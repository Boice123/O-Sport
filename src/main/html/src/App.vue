<template>
  <div id="app">
    <form method="POST" action="http://localhost:8090/admin/save" enctype="multipart/form-data">
      <input type="text" name="loginName"/>
      <input type="submit" value="提交"/>
    </form>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
