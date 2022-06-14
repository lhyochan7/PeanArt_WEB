<template>
    <v-card elevation="0">
        <v-toolbar elevation="0">
        <router-link style="text-decoration:none; color:black;" to="/"><h2>PeanArt</h2></router-link>
        <v-spacer></v-spacer>
        <v-btn text x-large>상품 제작</v-btn>
        <v-menu>
          <template v-slot:activator="{on, attrs}">
            <v-btn text x-large v-bind="attrs" v-on="on">목록<v-icon>mdi-menu-down</v-icon></v-btn>
          </template>
          <v-list>
            <router-link style="text-decoration:none; color:black;" to="/exhib/list?kind=0">
              <v-list-item>
                <v-list-item-title>전체</v-list-item-title>
              </v-list-item>
            </router-link>
            <router-link style="text-decoration:none; color:black;" to="/exhib/list?kind=1">
              <v-list-item>
                <v-list-item-title>대학 전시회</v-list-item-title>
              </v-list-item>
            </router-link>
            <router-link style="text-decoration:none; color:black;" to="/exhib/list?kind=2">
              <v-list-item>
                <v-list-item-title>개인 전시회</v-list-item-title>
              </v-list-item>
            </router-link>
            <router-link style="text-decoration:none; color:black;" to="/exhib/list?kind=3">
              <v-list-item>
                <v-list-item-title>기타</v-list-item-title>
              </v-list-item>
            </router-link>
          </v-list>
        </v-menu>
        <router-link style="text-decoration:none;" to="/myPage"><v-btn text x-large>내 정보</v-btn></router-link>
        <div v-if="usrId != ''">
          <v-btn text x-large @click="logOut()">로그아웃</v-btn>
        </div>
        <div v-else>
          <router-link style="text-decoration:none;" to="/login"><v-btn text x-large>로그인</v-btn></router-link>
        </div>
        </v-toolbar>
    </v-card>
</template>

<script>


export default {
  name: 'nav_bar',

  components: {
  },
  methods: {
    logOut: function() {
      this.usrId='';
      sessionStorage.removeItem('usrId');
      this.$router.push('/main');
    }
  },
  data: () => ({
    //
    usrId: '',
  }),
  created() {
    this.usrId = sessionStorage.getItem('usrId') || '';
  }
};
</script>