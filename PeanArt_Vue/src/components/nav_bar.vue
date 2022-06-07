<template>
    <v-card elevation="0">
        <v-toolbar elevation="0">
        <h2>PeanArt</h2>
        <v-spacer></v-spacer>
        <v-btn text x-large>상품 제작</v-btn>
        <v-btn text x-large>목록</v-btn>
        <v-btn text x-large>내 정보</v-btn>
        <v-btn text x-large>나가기</v-btn>
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