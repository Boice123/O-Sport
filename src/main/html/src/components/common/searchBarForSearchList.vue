<template>
  <div class="searchBarContainer">
    <div class="searchBarLeft">
      O-Sport
    </div>
    <div class="searchBarRight">
     <div class="searchBox">
         <el-cascader
          class="cascader"
          expand-trigger="hover"
          :options="options"
          v-model="selectedOptions2"
          @change="handleChange">
        </el-cascader>
        <div class="inputSearchBox">
            <input class="searchInput" type="text" v-model="searchKey"/>
            <button class="searchButton" @click="show">搜索</button>
            <ul class="searchResultUl">
                <li class="searchResultLi" v-for="(result, key) in searchResult">{{result.tripname}}</li>
            </ul>
        </div>
     </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios'
import { API_searchTripURL } from '../../constants/index.js'
  export default {
    name: 'searchBar',
    data() {
        return {
            tripprovice: '',
            tripcity: '',
            searchKey: '',
            searchResult: [],
            options: [
            {
                value: '取消选择',
                label: '取消选择'
            },
            {
            value: '广东',
            label: '广东',
            children: [
              {
                value: '江门',
                label: '江门'
              }, 
              {
                value: '广州',
                label: '广州',
              },
              {
                value: "深圳",
                label: "深圳",
              }
            ]
          },
          {
            value: '湖南',
            label: '湖南',
            children: [
              {
                value: '长沙',
                label: '长沙'
              }, 
              {
                value: '重庆',
                label: '重庆',
              },
              {
                value: "南京",
                label: "南京",
              }
            ],
          }
        ],
        value: '',
        selectedOptions2: [],
        }
    },
    watch: {
        searchKey: function() {
            this.getsearchLi()
        }
    },
    methods: {
        show() {
            let data = {
                tripprovice: this.tripprovice,
                tripcity: this.tripcity,
                searchKey: this.searchKey
            }
            this.$emit('getParams',data);
        },
        handleChange(value) {
            this.tripprovice = value[0]
            this.tripcity = value[1]
        },
        getsearchLi() {
            if(this.tripprovice == '取消选择') {
                this.tripprovice = ''
                this.tripcity = ''
            }
            if(this.searchKey == '') {
                this.searchResult = []
                return
            }
            var params = new FormData()
            params.append('tripprovice',this.tripprovice)
            console.log("this.tripprovice"+this.tripprovice)
            params.append('tripcity',this.tripcity)
            params.append('searchKey',this.searchKey)
            //根据地区和关键字找出Trip
            axios({
              method:'post',
              url:API_searchTripURL,
              data: params
            })
              .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                  this.searchResult = response.data.data
                }
            })
        }
    }
  }
</script>

<style>
.searchBarContainer {
  width: 100%;
  background: url('../../assets/images/bgc/campbgc.jpg') no-repeat;
  background-size: cover;
  /* background: #333; */
  display: flex;
  align-items: center;
  color: #fff;
  padding-top: 2rem;
  padding-bottom: 2rem;
}

.searchBarLeft {
    flex: 1;
    font-size: 4rem;
    padding: 1rem auto;
}

.searchBarRight {
    flex: 2;
}

.searchBox {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
   
}

.searchBoxLeft {
    color: #fdd000;
}

.searchSelectLeft {
    width: 5rem;
    height: 2.1rem;
    background: url("../../assets/images/pulldown.svg") no-repeat;
    background-position-x: 90%;
    background-position-y: center;
    background-size: 0.5rem 0.5rem;
    appearance:none;
    -moz-appearance:none;
    -webkit-appearance:none;
    color: #fdd000;
    font-size: 1rem;
    padding-left: 1rem;
    border: none;
}

.searchBoxRight {
    border: 2px solid #fdd000;
    display: flex;
    align-items: center;
    background: #333;
}

.searchSelectRight {
    width: 4rem;
    height: 2.1rem;
    background: url("../../assets/images/pulldown.svg") no-repeat;
    background-position-x: 90%;
    background-position-y: center;
    background-size: 0.5rem 0.5rem;
    appearance:none;
    -moz-appearance:none;
    -webkit-appearance:none;
    color: #696767;
    font-size: 0.9rem;
    padding-left: 1rem;
    border: none;
}
.inputSearchBox {
    border-radius: 5px;
    /* overflow: hidden; */
    margin-left: 1rem;
    position: relative;
}
.searchInput {
    height: 2.5rem;
    width: 15rem;
    font-size: 1rem;
    /* border: none; */
    background: #fff;
    color: #000;
    padding: 0 5px;
    border: 1px solid gray;
}
.searchButton {
    padding: 0.6rem 2.5rem;
    color: #000;
    font-size: 1rem;
    font-weight: 600;
    background: #fdd000;
}
.cascader {
    width: 7rem;
    height: 2.35rem;
    border-radius: 10px;
}
.searchResultUl {
    position: absolute;
    left: 0.04rem;
    top: 2.6rem;
    background: #fff;
}
.searchResultLi {
    height: 2rem;
    color: #000;
    width: 15.2rem;
    border-bottom: 1px solid #ebebe8;
    padding-left: 0.5rem;
    display: flex;
    align-items:  center;
}
</style>
