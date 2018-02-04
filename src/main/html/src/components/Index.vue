<template>
  <div class="index">
    <form method="post" action="/admin/save" enctype="multipart/form-data">
      <input type="username" v-model="adminname"/>
      <input type="submit" value="提交"/>
    </form>
  </div>
</template>

<script>
export default {
  name: 'index',
  data () {
    return {
      adminname: ""
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
