<template>
  <v-app>
      <nav_bar></nav_bar>
      <v-container>
          <v-row>
              <v-col>
                  <exhib_card :datass="list"></exhib_card>
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
        list: {
            seq:'1',
            title: '서울대 전시회',
            startDate: '2022-06-01',
            endDate: '2022-07-15',
            location:'서울시',
            imgSrc: '../assets/',
        },
    }),

}
</script>

<style>

</style>