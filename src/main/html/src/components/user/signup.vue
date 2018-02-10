<template>
  <div id="app">
    <form method="POST" action="http://localhost:8090/user/save" enctype="multipart/form-data">
      电话：<input type="tel" name="tel"/>
      密码：<input type="password" name="password"/>
      昵称：<input type="text" name="username"/>
      <input type="submit" value="提交"/>
    </form>
  </div>
</template>

<script>
  export default {
    name: 'signup'
  }
</script>

<style>
</style>
