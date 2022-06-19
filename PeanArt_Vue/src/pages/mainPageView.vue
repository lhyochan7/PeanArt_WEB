<template>
  <v-app>
    <nav_bar/>
    <v-main>
      <v-container>      
        <v-row class="mt-16" justify="center">
          <v-col md="12">
            <v-card>
              <v-img :src="main_item.cover">
                <v-container fill-height>
                  <v-layout>
                    <v-card>
                      <v-card-title>테스트 전시회</v-card-title>
                      <v-card-text class="mt-16">2022년 10월 12일, 수요일</v-card-text>
                      <v-card-text class="mt-8 font-weight-bold">달리 반 피카소</v-card-text>
                    </v-card>
                    <v-spacer/>
                    <v-img src="../assets/new_icon.png" max-width="180" max-height="180"></v-img>
                  </v-layout>
                </v-container>
              </v-img>
            </v-card>
          </v-col>
        </v-row>
        <v-divider class="mt-12"/>
        <v-row class="mt-8" justify="center">
          <v-col sm="10" class="text-center">
            <h2 class="font-weight-light">당신을 위해 준비한 AI 추천!</h2>
          </v-col>
        </v-row>
        <router-link style="text-decoration:none; color:black;" to="/airecommend">
          <v-row>
            <v-col sm="3">
              <v-img src="../assets/ai_1.png" class="rounded-xl"></v-img>
            </v-col>
            <v-col sm="3">
              <v-img src="../assets/ai_2.png" class="rounded-xl"></v-img>
            </v-col>
            <v-col sm="3">
              <v-img src="../assets/ai_3.png" class="rounded-xl"></v-img>
            </v-col>
            <v-col sm="3">
              <v-img src="../assets/ai_4.png" class="rounded-xl"></v-img>
            </v-col>
          </v-row>
        </router-link>
        <v-row>
          <Exhib_card/>
          <Exhib_card/>
          <Exhib_card/>
        </v-row>
      </v-container>
    </v-main>
    <foot_bar/>
  </v-app>
</template>
<script>
import Exhib_card from '../components/exhib_card.vue';
import nav_bar from '../components/nav_bar.vue';
import foot_bar from '../components/foot_bar.vue';
export default {
  name: 'mainPage',

  components: {
    Exhib_card,
    nav_bar,
    foot_bar
},

  data: () => ({
    main_item: {
      'title':'테스트 전시회',
      'date':'2022년 10월 12일, 수요일',
      'artist':'달리 반 피카소',
      'cover': require('../assets/cover.png'),
    }
    //
  }),
};
</script>
