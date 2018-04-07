<template>
    <div class="checkOrderContainer">
        <div class="triporderDetailBox">
            <div class="checkOrdertitle">用户订单信息</div>
             <el-table class="tripDetail"
                :data="triporder"
                style="width: 100%"
                tooltip-effect="dark"
            >
                    <el-table-column type="expand">
                        <template slot-scope="props">
                            <el-form label-position="left" inline class="demo-table-expand" >
                            <el-form-item label="出团名称">
                                <span>{{ props.row.trip.tripname }}</span>
                            </el-form-item>
                            <el-form-item label="出团单价">
                                <span>{{ props.row.trip.tripprice }}</span>
                            </el-form-item>
                            <el-form-item label="出团时间">
                                <span>{{ props.row.triptime.triptime  }}</span>
                            </el-form-item>
                            <el-form-item label="参与人数">
                                <span>{{ props.row.triporderitem.people  }}</span>
                            </el-form-item>
                            </el-form>
                        </template>
                    </el-table-column>
                <el-table-column
                type="selection"
                width="55">
                </el-table-column>
                
                <el-table-column
                prop="triporderid"
                label="订单号"
                width="300">
                </el-table-column>
                <el-table-column
                prop="tripordertime"
                label="下单时间"
                width="100">
                <!-- <img :src={tripimg} /> -->
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
                <el-table-column label="操作" prop="tripid">
                    <template slot-scope="scope">
                        <el-button
                        size="mini"
                        @click="cancel(triporder[scope.$index].triporderid, triporder[scope.$index].triporderstatus)">取消订单</el-button>
                        <el-button
                        size="mini"
                        type="danger"
                        @click="sure(triporder[scope.$index].triporderid, triporder[scope.$index].triporderstatus)">确认订单</el-button>
                    </template>
                </el-table-column>
            </el-table>
            <!-- <div class="block">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page=currentPage
                    :page-sizes="[5, 10, 20]"
                    :page-size="5"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total=shopTripCount>
                </el-pagination>
            </div> -->
        </div>
  </div>
</template>

<script>
import {API_getUserTripOrderURl, API_gettriporderitemURl, API_cancelOrderURl, API_sureOrderURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'checkOrder',
    data() {
      return {
          triporder: [{
              triporderid: '',
              tripordertotal: '',
              tripordertime: '',
              triporderstatus: '已下单',  
            //   tripname: '',
              trip: {
                  tripname: '',
                  tripprice: ''
              },
              triptime: {
                  triptime: ''
              },
              triporderitem: {
                  people: ''
              }   
          }],
      }
    },
    created() {
        //获取triporder信息
        axios({
            method:'post',
            url:API_getUserTripOrderURl
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.triporder = response.data.data
                // this.triporder.trip = response.data.data.trip
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                }); 
            }else {
                this.$message.error('获取订单信息失败，请稍后重试');
            }        
        }).catch((err) => {
            console.log(err)
        })  
        
    },
    methods: {
        gotoIndex() {
            this.$router.push('/')
        },
        cancel(triporderid, triporderstatus) {
            if(triporderstatus === '已取消') {
                this.$message({
                        message: "您已取消订单了，请不要重复操作",
                        type: 'warning'
                });
                return
            }
            if(triporderstatus === '已确认') {
                this.$message({
                        message: "您已确认订单了，不可取消",
                        type: 'warning'
                });
                return
            }

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
                    this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    }); 
                }else {
                    this.$message.error('取消订单失败，请稍后重试');
                }        
            }).catch((err) => {
                console.log(err)
            })  
        },
        sure(triporderid, triporderstatus) {
            if(triporderstatus === '已取消') {
                this.$message({
                        message: "您已经取消订单，操作无效",
                        type: 'warning'
                });
                return
            }
            if(triporderstatus === '已确认') {
                this.$message({
                        message: "您已确认订单了，请不要重复操作",
                        type: 'warning'
                });
                return
            }
            var params = new URLSearchParams();
            params.append('triporderid', triporderid);
            //取消订单信息
            axios({
                method:'post',
                url:API_sureOrderURl,
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
                    this.$message.error('取消订单失败，请稍后重试');
                }        
            }).catch((err) => {
                console.log(err)
            })  
        }
    }
  }
</script>

<style>
.checkOrderContainer {
    width: 50rem;
    margin: 2rem auto;
}
.triporderDetailBox {
    width: 60rem;
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
</style>