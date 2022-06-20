<template>
  <v-app>
    <nav_bar/>
    <v-main>
      <v-container>      
        <v-row class="mt-16" justify="center">
          <v-col md="12">
            <v-card>
              <router-link style="text-decoration:none; color:black;" :to="{name: 'exhibitDetailView', params: {id: mainExhib.exhibSeq}}">
                <v-img :src="getImgURL(mainExhib.fileDirName + '/' + mainExhib.fileName)" max-height="400">
                  <v-container fill-height>
                    <v-layout>
                      <v-card>
                        <v-card-title>{{mainExhib.exhibTitle}}</v-card-title>
                        <v-card-text class="mt-16">{{mainExhib.exhibStartDate}}</v-card-text>
                        <v-card-text class="mt-8 font-weight-bold">{{mainExhib.exhibSimpleExp}}</v-card-text>
                      </v-card>
                      <v-spacer/>
                      <v-img src="../assets/new_icon.png" max-width="180" max-height="180"></v-img>
                    </v-layout>
                  </v-container>
                </v-img>
              </router-link>
            </v-card>
          </v-col>
        </v-row>
        
        <v-row v-if="isLoaded==false" class="mt-12">
          <v-skeleton-loader
          class="mx-auto"
          type="card"
          height="100%"
          width="100%"
          ></v-skeleton-loader>
        </v-row>
        <v-row v-else class="mt-12">
          <v-col class="d-flex child-flex" cols="3" v-for="(item, index) in exhibList" v-bind:key="index">
            <Exhib_card :exhibData="item"/>
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
            <v-col sm="3" v-for="i in 4" :key="i">
              <v-img :src="getImgURL('AI_images/'+i+'.png')" class="rounded-xl" max-height="280" max-width="400"></v-img>
            </v-col>
          </v-row>
        </router-link>
      </v-container>
    </v-main>
    <foot_bar/>
  </v-app>
</template>
<script>
import Exhib_card from '../components/exhib_card.vue';
import nav_bar from '../components/nav_bar.vue';
import foot_bar from '../components/foot_bar.vue';
import axios from 'axios';
export default {
  name: 'mainPage',

  components: {
    Exhib_card,
    nav_bar,
    foot_bar
},

  data: () => ({
    mainExhib: {
        "exhibSeq": 9,
        "exhibTitle": "seoul_design",
        "exhibSimpleExp": "seoul_design",
        "exhibStartDate": "2020-10-10 01:01:01",
        "exhibEndDate": "2020-10-10 01:01:01",
        "exhibLocation": "seoul_design",
        "fileDirName": "63c9e7d8-9507-489c-89d1-a30718cf8cc3_seoul_design",
        "fileName": "63c9e7d8-9507-489c-89d1-a30718cf8cc3_poster.PNG"
    },
    exhibList:[],
    // 로딩 확인
    isLoaded: false,
  }),
  methods: {
    getImgURL(pic) {
      return 'http://localhost:8080/imagePath/' + pic.replace('PNG', 'png')}
  },
  mounted(){
    axios.get('/mainPoster').then(response=>{
      console.log(response);
      if(response.status==200){
        var data = response.data
        this.mainExhib = data.shift()
        this.exhibList = data
        this.isLoaded = true
      }
    })
  }
};
</script>
