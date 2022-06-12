<template>
    <v-col sm="4">
      <router-link style="text-decoration:none; color:black;" :to="`${datass.seq}/detail.do`">
          <v-card
            class="rounded-xl mt-4 mb-4"
            elevation="0"
            outlined
          >
              <v-img
              :src="require(`${datass.imgSrc}ai_1.png`)"
              height="200px"
              >
              </v-img>
              <v-card-text class="ml-n4 mb-n2">
                  <v-card-title class="mt-n4 mb-3" style="color:black;">{{datass.title}}</v-card-title>
                  <v-card-subtitle>{{datass.startDate}} ~ {{datass.endDate}}</v-card-subtitle>
                  <v-card-text>{{datass.location}}</v-card-text>
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
  props : {
    datass: Object
  },
  data: () => ({
    //
  }),
};
</script>

<style>

</style>