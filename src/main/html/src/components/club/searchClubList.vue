<template>
<div>
    <ClubHeadForSearchList @getParams = 'getParams'/>
  <div class="searchContent">
    <div class="searchBox" 
        v-for="(search, key) in searchList"
        @mouseover="changeSearchCover(search.clubid)" 
        @mouseout="changeSearchCover('')"
        @click="gotoClubPage(search.clubid)"
    >
        <img 
            :class="{'searchImg': searchCover != search.clubid,'searchImgActive': searchCover == search.clubid}"
            src="../../assets/images/bgc/manycamp.jpg"
        />
        <div class="searchTextMask"></div>
        <div :class="{'searchText': searchCover != search.clubid, 'searchTextActive': searchCover == search.clubid}">
            <h2 class="searchname">{{search.clubname}}</h2>
            <p class="searchnum"><br>已有{{search.clubpeople}}名会员</p>
            <span 
                :class="{'joinSearch': searchCover != search.clubid,'joinSearchActive': searchCover == search.clubid}"
            >+ 关注
            </span>
        </div>
    </div>
  </div>
  </div>
</template>

<script>
import axios from 'axios'
import { API_searchClubURL } from '../../constants/index.js'
import ClubHeadForSearchList from './clubHeadForSearchList'
export default {
    data() {
        return {
            searchCover: '',
            searchList: []
        }
    },
    watch: {
    
    },
    created() {
        this.getResult()
    },
    methods: {
        gotoClubPage(clubid) {
            this.$router.push({name:'clubMainPage',params: {clubid}})
        },
        changeSearchCover(text) {
            this.searchCover = text
        },
        getParams(...data) {
            console.log(data)
            console.log(data[0].searchKey)
            var params = new URLSearchParams();
            params.append('searchKey',data[0].searchKey)
            
            axios({
                method:'post',
                url:API_searchClubURL,
                params
            })
            .then((response) => {
                console.log(response.data)
                if(response.data.code == 0) {
                    this.searchList = response.data.data
                }else if(response.data.code == 1) {
                    this.$message({
                        message: response.data.msg,
                        type: 'warning'
                    });
                }else {
                    this.$message.error('搜索信息失败，请换个关键词');
                }        
            }).catch((err) => {
                console.log(err)
            })
        },
        getResult() {
         var params = new URLSearchParams();
         params.append('searchKey',this.$route.params.searchKey)
         axios({
            method:'post',
            url:API_searchClubURL,
            params
        })
        .then((response) => {
            console.log(response.data)
            if(response.data.code == 0) {
                this.searchList = response.data.data
            }else if(response.data.code == 1) {
                this.$message({
                    message: response.data.msg,
                    type: 'warning'
                });
            }else {
                this.$message.error('搜索信息失败，请换个关键词');
            }        
        }).catch((err) => {
            console.log(err)
        })
      },
      gotoClubPage(clubid) {
          this.$router.push({name: 'clubMainPage', params: {clubid}})
      }
    },
    components: {
      ClubHeadForSearchList
    }
}
</script>

<style>
.searchContent {
    width: 80%;
    margin: 3rem auto;
    background: pink;
    overflow: hidden;
    flex-direction: column;
    display: grid;
    grid-template-columns: 1fr 1fr 1fr;
    grid-column-gap: 20px;
    grid-row-gap: 20px;
    /* grid-template-rows:1fr 1fr; */
}
.searchBox {
    width: 100%;
    height: 14rem;
    display: flex;
    flex-direction: row;
    position: relative;
    overflow: hidden;
}
.searchImg {
    position: absolute;
    width: 110%;
    height: 14rem;
    left: -10%;
    top: 0;
    animation: clubImghide .5s;
    -moz-animation: clubImghide .5s;	/* Firefox */
    -webkit-animation: clubImghide .5s;	/* Safari 和 Chrome */
    -o-animation: clubImghide .5s;	/* Opera */
}
.searchImgActive {
    position: absolute;
    width: 110%;
    height: 14rem;
    left: 0;
    top: 0;
    animation: clubImgshow .5s;
    -moz-animation: clubImgshow .5s;	/* Firefox */
    -webkit-animation: clubImgshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubImgshow .5s;	/* Opera */
}
@-webkit-keyframes clubImgshow /* Safari 和 Chrome */
{
  from { left: -10% }
  to { left: 0 }
}
@-webkit-keyframes clubImghide /* Safari 和 Chrome */
{
  from { left: 0  }
  to { left: -10%  }
}
.searchTextMask {
    width: 100%;
    height: 14rem;
    background: rgb(0,0,0,.2);
    position: absolute;
    left: 0;
    top: 0;
}
.searchText {
    width: 100%;
    height: 17rem;
    position: absolute;
    left: 0;
    top: 0;
    animation: clubTexthide .5s;
    -moz-animation: clubTexthide .5s;	/* Firefox */
    -webkit-animation: clubTexthide .5s;	/* Safari 和 Chrome */
    -o-animation: clubTexthide .5s;	/* Opera */
}
.searchTextActive {  
    width: 100%;
    height: 17rem;
    position: absolute;
    left: 0;
    top: -10%;
    animation: clubTextshow .5s;
    -moz-animation: clubTextshow .5s;	/* Firefox */
    -webkit-animation: clubTextshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubTextshow .5s;	/* Opera */
}
@-webkit-keyframes clubTextshow /* Safari 和 Chrome */
{
  from { top: 0 }
  to { top: -10%}
}
@-webkit-keyframes clubTexthide /* Safari 和 Chrome */
{
  from { top: -10% }
  to { top: 0 }
}
.searchname {
    color: #fff;
    text-align: left;
    padding-left: 1rem;
    position: absolute;
    top: 70%;
}
.searchnum {
    color: #fff;
    text-align: left;
    padding-left: 1rem;
    position: absolute;
    top: 78%;
}
.joinSearch {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: 2%;
    top: 90%;
    width: 4rem;
    height: 2rem;
    background: yellow;
    color: #000;
    border-radius: 10px;
    animation: clubjoinhide .5s;
    -moz-animation: clubjoinhide .5s;	/* Firefox */
    -webkit-animation: clubjoinhide .5s;	/* Safari 和 Chrome */
    -o-animation: clubjoinhide .5s;	/* Opera */
}
.joinSearchActive {
    display: flex;
    justify-content: center;
    align-items: center;
    position: absolute;
    right: 2%;
    top: 75%;
    width: 4rem;
    height: 2rem;
    background: yellow;
    color: #000;
    border-radius: 10px;
    animation: clubjoinshow .5s;
    -moz-animation: clubjoinshow .5s;	/* Firefox */
    -webkit-animation: clubjoinshow .5s;	/* Safari 和 Chrome */
    -o-animation: clubTextshow .5s;	/* Opera */
}
@-webkit-keyframes clubjoinshow /* Safari 和 Chrome */
{
  from { top: 90% }
  to { top: 75%}
}
@-webkit-keyframes clubjoinhide /* Safari 和 Chrome */
{
  from { top: 75% }
  to { top: 90% }
}
</style>
