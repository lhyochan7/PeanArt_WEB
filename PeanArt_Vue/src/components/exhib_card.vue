<template>
      <router-link style="text-decoration:none; color:black;" :to="`${exhibData.seq}/detail`">
          <v-card
            class="rounded-xl mt-4 mb-4"
            elevation="0"
            outlined
          >
              <v-img
              :src="`${exhibData.imgSrc}ai_1.png`"
              height="200px"
              >
              </v-img>
              <v-card-text class="ml-n4 mb-n2">
                  <v-card-title class="mt-n4 mb-3" style="color:black;">{{exhibData.title}}</v-card-title>
                  <v-card-subtitle>{{exhibData.startDate}} ~ {{exhibData.endDate}}</v-card-subtitle>
                  <v-card-text>{{exhibData.location}}</v-card-text>
              </v-card-text>
          </v-card>
      </router-link>
</template>

<script>
export default {
  name: 'exhib_card',

  components: {
  },
  props : {
    exhibData: Object
  },
  data: () => ({
    //
  }),
};
</script>

<style>

</style>