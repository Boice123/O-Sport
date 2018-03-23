<template>
  <div class="searchBarContainer">
    <div class="searchBarLeft">
      O-Sport
    </div>
    <div class="searchBarRight">
     <div class="searchBox">
        <div class="searchBoxLeft">
            <select class="searchSelectLeft">
            <option class="searchOptionLeft" value="1" selected="selected">广州</option>
            <option class="searchOptionLeft"
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </option>
            </select>
        </div>
        <div class="searchBoxRight">
            <select class="searchSelectRight">
            <option value="1" selected="selected">全部</option>
            <option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                :value="item.value">
            </option>
            </select>
            <input class="searchInput" type="text"/>
            <button class="searchButton" @click="show">搜索</button>
        </div>
     </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'searchBar',
    data() {
        return {
            options: [{
                value: '2',
                label: '上海'
            }, {
                value: '3',
                label: '深圳'
            }, {
                value: '4',
                label: '杭州'
            }, {
                value: '5',
                label: '重庆'
            }],
            value: ''
        }
    },
    methods: {
        show() {
            // console.log(this.)
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

.searchInput {
    height: 1.5rem;
    font-size: 1rem;
    border: none;
    background: #333;
    color: #fff;
}

.searchButton {
    padding: 0.5rem 2.5rem;
    color: #000;
    font-size: 1rem;
    font-weight: 600;
    background: #fdd000;
}
</style>
