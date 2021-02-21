<template>
<div id="app">
 <router-view />
</div>
</template>

<script lang="ts">

import { Component, Vue } from "vue-property-decorator";
import { namespace } from "vuex-class";
const Auth = namespace("Auth");

@Component
export default class App extends Vue {  
}
</script>