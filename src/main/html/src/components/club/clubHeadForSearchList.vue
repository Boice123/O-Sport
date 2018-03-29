<template>
  <div class="clubHead">
        <div class="clubinputDiv">
            <input type="text" class="clubinput" v-model="searchKey" placeholder="请输入部落关键字"/>
            <div class="clubspan" @click="show">
                <img class="sousuoImg" src="../../assets/images/sousuo.svg"/>
            </div>
            <ul class="clubsearchResultUl">
                <li class="clubsearchResultLi" v-for="(result, key) in searchResult" @click="setSearchKey(result.clubname)">{{result.clubname}}</li>
            </ul>
        </div>
      </div>
</template>

<script>
import axios from 'axios'
import { API_searchClubURL } from '../../constants/index.js'
export default {
    data() {
        return {
            searchKey: '',
            searchResult: [],
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
                searchKey: this.searchKey
            }
            console.log("this.searchKey" + this.searchKey)
            this.$emit('getParams',data);
        },
        setSearchKey(clubname) {
            this.searchResult = []
            this.searchKey = clubname
            
        },
        getsearchLi() {
            if(this.searchKey == '') {
                this.searchResult = []
                return
            }
            var params = new FormData()
            params.append('searchKey',this.searchKey)
            //根据关键字找出Club
            axios({
              method:'post',
              url:API_searchClubURL,
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
.clubHead {
    width: 100%;
    height: 300px;
    background: url('../../assets/images/bgc/manycamp.jpg');
    display: flex;
    justify-content: center;
    align-items: center;
}
.inputDiv {
    display: flex;
    justify-content: center;
    align-items: center;
    border: 5px solid #d9d6d6;
    border-radius: 5px;
}
.clubinput {
    width: 20rem;
    height: 3rem;
    border: none;
    padding-left: 10px;
    padding-right: 10px;
    font-size: 15px;
}
.clubspan {
    display: flex;
    justify-content: center;
    align-items: center;
    background: red;
    width: 4rem;
    height: 3rem;
}
.sousuoImg {
    width: 2rem;
    height: 2rem;
}
.clubsearchResultUl {
    width: 21rem;
    position: absolute;
    left: 0;
    top: 3.2rem;
    background: #fff;
    z-index: 10;
}
.clubsearchResultLi {
    height: 3rem;
    width: 100%;
    color: #000;
    border-bottom: 1px solid #ebebe8;
    padding-left: 0.8rem;
    padding-right: 0.8rem;
    display: flex;
    align-items:  center;
}
</style>
