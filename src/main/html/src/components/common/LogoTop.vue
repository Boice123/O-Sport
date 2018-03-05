<template>
  <div class="logoTopContainer">
    <div class="logoTopWrap">
      <div class="logoTopLeft">
        O-Sport
      </div>
      <div class="logoTopRight">
        预订热线:9:00~23:00<br><br>020-123456789
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    name: 'searchBar',
    data() {
        return {
            
        }
    }
  }
</script>

<style>
.logoTopContainer {
  width: 100%;
  background: #333;
  align-items: center;
  color: #fff;
  padding-top: 1rem;
  padding-bottom: 1rem;
  text-align: left;
}

.logoTopWrap {
  width: 40%;
  display: flex;
  align-items: center;
}

.logoTopLeft {
    flex: 1;
    font-size: 4rem;
    padding-top: 1rem;
    padding-bottom: 1rem;
    padding-left: 2rem;
}

.logoTopRight {
    flex: 1;
    color: #fdd000;
}
</style>
