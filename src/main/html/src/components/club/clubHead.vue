<template>
  <div class="clubHead">
        <div class="inputDiv">
            <input type="text" class="clubinput" placeholder="请输入部落关键字"/>
            <div class="clubspan">
                <img class="sousuoImg" src="../../assets/images/sousuo.svg"/>
            </div>
        </div>
      </div>
</template>

<script>

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
</style>
