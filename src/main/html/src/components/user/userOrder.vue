<template>
    <div class="checkOrderContainer">
        <div class="triporderDetailBox">
            <div class="checkOrdertitle">用户订单信息</div>
             <el-table class="tripDetail"
                :data="triporder"
                style="width: 100%"
                tooltip-effect="dark"
            >
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
import {API_getUserTripOrderURl} from '../../constants/index.js'
import axios from 'axios'
  export default {
    name: 'checkOrder',
    data() {
      return {
          triporder: [{
              triporderid: '',
              tripordertotal: '',
              tripordertime: '',
              triporderstatus: 1,            
          }],
          triporderitem:[]
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
        getOrderItem(triporderitemid) {
            var params = new URLSearchParams();
            params.append('triporderitemid',triporderitemid) 
            //获取triporderitem信息
            axios({
                method:'post',
                url:API_getTripOrderItemURl,
                params
            })
            .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                    this.triporderitem = response.data.data
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
        gotoIndex() {
            this.$router.push('/')
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