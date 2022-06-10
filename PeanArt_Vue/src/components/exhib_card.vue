<template>
    <v-col sm="4">
  <router-link style="text-decoration:none; color:black;" to="id/detail.do">
      <v-card
        class="rounded-xl mt-4 mb-4"
        elevation="0"
        outlined
      >
          <v-img
          src="../assets/ai_1.png"
          height="200px"
          >
          </v-img>
          <v-card-text class="ml-n4 mb-n2">
              <v-card-title class="mt-n4 mb-3" style="color:black;">서울대 전시회</v-card-title>
              <v-card-subtitle>2022-06-01 ~ 2022-07-24</v-card-subtitle>
              <v-card-text>위치</v-card-text>
          </v-card-text>
      </v-card>
  </router-link>
    </v-col>
</template>

<script>
export default {
  name: 'exhib_card',

  components: {
},

  data: () => ({
    //
  }),
};
</script>

<style>

</style>