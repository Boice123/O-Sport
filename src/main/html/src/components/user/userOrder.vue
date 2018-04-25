<template>
    <div class="checkOrderContainer">
        <div class="orderNav">
                <div :class="{'orderNavSectionActive': click == '全部','orderNavSection': click != '全部'}" @click="clickStatus('全部')">全部</div>
                <div :class="{'orderNavSectionActive': click == '未付款','orderNavSection': click != '未付款'}" @click="clickStatus('未付款')">未付款</div>
                <div :class="{'orderNavSectionActive': click == '已付款','orderNavSection': click != '已付款'}" @click="clickStatus('已付款')">已付款</div>
                <div :class="{'orderNavSectionActive': click == '已取消','orderNavSection': click != '已取消'}" @click="clickStatus('已取消')">已取消</div>
                <div :class="{'orderNavSectionActive': click == '已关闭','orderNavSection': click != '已关闭'}" @click="clickStatus('已关闭')">已关闭</div>
        </div>
        <div class="triporderDetailBox">           
            <div class="checkOrdertitle">用户订单信息</div>
             <el-table class="orderDetail"
                :data="triporder"
                style="width: 100%"
                tooltip-effect="dark"
            >
                <el-table-column
                    type="index"
                    width="50">
                </el-table-column>
                <el-table-column
                label="出团图片"
                width="100">
                <template slot-scope="scope">
                   <img class="miniimg" :src="scope.row.trip.tripimg"/>
                </template>
                </el-table-column>
                <el-table-column
                label="出团名称"
                width="100">
                <template slot-scope="scope">
                   <span>{{ scope.row.trip.tripname}}</span>
                </template>
                </el-table-column>
                <el-table-column
                label="出团时间"
                width="100">
                 <template slot-scope="scope">
                   <span>{{triporder[scope.$index].trip.triptime}}</span>
                </template>
                </el-table-column>
                <el-table-column
                label="出团单价"
                width="100">
                 <template slot-scope="scope">
                   <span>{{triporder[scope.$index].trip.tripprice}}</span>
                </template>
                </el-table-column>
                <el-table-column
                prop="people"
                label="参与人数"
                width="100">
                </el-table-column>
                <el-table-column
                prop="tripordertime"
                label="下单时间"
                width="100">
                </el-table-column>
                <el-table-column
                prop="tripordertotal"
                label="订单总价"
                width="150">
                </el-table-column>
                <el-table-column
                prop="triporderstatus"
                label="订单状态"
                width="80">
                </el-table-column>
                <el-table-column prop="tripid">
                    <template slot-scope="scope">
                        <el-button
                        size="mini"
                        v-if="triporder[scope.$index].triporderstatus === '未付款'"
                        @click="cancel(triporder[scope.$index].triporderid, triporder[scope.$index].triporderstatus)">取消订单</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </div>
  </div>
</template>

<script>
import {API_getUserTripOrderURl, API_gettriporderitemURl, API_cancelOrderURl, API_sureOrderURl, API_evaluateOrderURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'checkOrder',
    data() {
      return {
          triporder: [{
              triporderid: '',
              tripordertotal: '',
              tripordertime: '',
              people:'',
              triporderstatus: '',  
              trip: {
                  tripname: '',
                  tripprice: '',
                  tripid: '',
                  triptime: '',
                  tripimg: ''
              }
          }],
          click: '全部'
      }
    },
    created() {
        this.getAllOrders('')      
    },
    methods: {
        clickStatus(text) {
            console.log(text)
            this.click = text
            this.getAllOrders(text)
        },
        gotoIndex() {
            this.$router.push('/')
        },
        // 获取用户所有订单  
        getAllOrders(click) {
            var params = new URLSearchParams();
            if(click == '全部') {
                click = ''
            }
            params.append('triporderstatus',click)
            axios({
                method:'post',
                url:API_getUserTripOrderURl,
                params
            })
            .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                    this.triporder = response.data.data
                }else if(response.data.code == 1) {
                    console.log(response.data.msg)
                }else {
                    console.log('获取订单信息失败，请稍后重试');
                }        
            }).catch((err) => {
                console.log(err)
            })  
        },
        // 取消订单
        cancel(triporderid, triporderstatus) {
            this.$confirm('是否要取消订单?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                var params = new URLSearchParams();
                params.append('triporderid', triporderid);
                //取消订单信息
                axios({
                    method:'post',
                    url:API_cancelOrderURl,
                    params
                })
                .then((response) => {
                    console.log(response.data)
                    if(response.data.code == 0) {
                        this.$message({
                            message: response.data.msg,
                            type: 'success'
                        }); 
                        setTimeout(() => {
                            location.reload()
                        },2000)  
                    }else if(response.data.code == 1) {
                        console.log(response.data.msg)
                    }else {
                        this.$message.error('取消订单失败，请稍后重试');
                    }        
                }).catch((err) => {
                    console.log(err)
                })  
            }).catch(() => {

            })
        },
        evaluate(tripid, triporderid) {
                console.log(tripid, triporderid)
                this.$prompt('请输入评价', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    inputPattern: /\S/,
                    inputErrorMessage: '评价不能为空'
                    }).then(({ value }) => {
                        var params = new URLSearchParams();
                        params.append('content', value)
                        params.append('tripid', tripid)
                        params.append('triporderid', triporderid);
                        //评价订单信息
                        axios({
                            method:'post',
                            url:API_evaluateOrderURl,
                            params
                        })
                        .then((response) => {
                            console.log(response.data)
                            if(response.data.code == 0) {
                                this.$message({
                                    message: response.data.msg,
                                    type: 'success'
                                }); 
                                setTimeout(() => {
                                    location.reload()
                                },2000)
                            }else if(response.data.code == 1) {
                                this.$message({
                                    message: response.data.msg,
                                    type: 'warning'
                                }); 
                            }else {
                                this.$message.error('评价订单失败，请稍后重试');
                            }        
                        }).catch((err) => {
                            console.log(err)
                        })  
                    }).catch(() => {
    
                });
        }    
    }
  }
</script>

<style>
.checkOrderContainer {
    width: 80rem;
    height: 50rem;
    margin: 2rem auto;
    display: flex;
}
.orderNav {
    width: 10rem;
    height: 15rem;
    margin-top: 3rem;
}
.orderNavSection {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 1rem;
}
.orderNavSectionActive {
    display: flex;
    align-items: center;
    justify-content: center;
    margin-top: 1rem;
    color: orange;
}
.triporderDetailBox {
    width: 70rem;
    box-shadow:4px 4px 10px gray;
    margin: 0 auto;
}
.checkOrdertitle {
    text-align: left;
    padding-left: 3rem;
    padding-top: 2rem;
    font-size: 1.5rem;
    font-weight: 500;
}
.el-table th>.cell {
  text-align: center
}
.orderDetail {
    display: flex;
    flex-direction: column;
}
.miniimg {
    width: 3rem;
    height:3rem;
}
</style>