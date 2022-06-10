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
            <router-link style="text-decoration:none; color:black;" to="/exhib/list.do?kind=0">
              <v-list-item>
                <v-list-item-title>전체</v-list-item-title>
              </v-list-item>
            </router-link>
            <router-link style="text-decoration:none; color:black;" to="/exhib/list.do?kind=1">
              <v-list-item>
                <v-list-item-title>대학 전시회</v-list-item-title>
              </v-list-item>
            </router-link>
            <router-link style="text-decoration:none; color:black;" to="/exhib/list.do?kind=2">
              <v-list-item>
                <v-list-item-title>개인 전시회</v-list-item-title>
              </v-list-item>
            </router-link>
            <router-link style="text-decoration:none; color:black;" to="/exhib/list.do?kind=3">
              <v-list-item>
                <v-list-item-title>기타</v-list-item-title>
              </v-list-item>
            </router-link>
          </v-list>
        </v-menu>
        <router-link style="text-decoration:none;" to="/myPage.do"><v-btn text x-large>내 정보</v-btn></router-link>
        <router-link style="text-decoration:none;" to="/login.do"><v-btn text x-large>로그인</v-btn></router-link>
        </v-toolbar>
    </v-card>
</template>

<script>

export default {
  name: 'nav_bar',

  components: {
  },

  data: () => ({
    //
  }),
};
</script>