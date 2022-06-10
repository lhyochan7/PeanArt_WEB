<template>
  <v-app>
      <nav_bar></nav_bar>
      <v-container>
          <v-row>
              <v-col>
                  <exhib_card></exhib_card>
              </v-col>
          </v-row>
      </v-container>
  </v-app>
</template>

<script>
import exhib_card from '../components/exhib_card.vue'
import nav_bar from '../components/nav_bar.vue'
export default {
    name: 'exhibitListView',
    components: { nav_bar, exhib_card },
    data: () => ({

    }),

}
</script>

<style>

</style>