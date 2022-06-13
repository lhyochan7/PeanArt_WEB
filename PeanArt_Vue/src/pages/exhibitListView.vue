<template>
  <v-app>
      <nav_bar></nav_bar>
      <v-container>
        <v-row no-gutters>
            <router-link to="/exhib/register" style="text-decoration:none; color:black;"><v-btn outlined x-large>전시회 등록</v-btn></router-link>
            <v-spacer/>
            <v-spacer/>
            <v-col md="2">
                <v-select outlined label="분류" :items="searchItem" item-text="name" item-value="value" v-model="searchSeletedItem"></v-select>
            </v-col>
            <v-col md="4">
                <v-text-field outlined clearable v-model="searchInput"></v-text-field>
            </v-col>
            <v-col md="1">
                <v-btn outlined x-large @click="searchByInput()">검색</v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col class="d-flex child-flex" cols="4" v-for="(item, i) in list" v-bind:key="i">
                <exhib_card :exhibData="item"></exhib_card>
            </v-col>
        </v-row>
      </v-container>
  </v-app>
</template>

<script>
import exhib_card from '../components/exhib_card.vue'
import nav_bar from '../components/nav_bar.vue'
import Axios from 'axios'
export default {
    name: 'exhibitListView',
    components: { nav_bar, exhib_card },
    data: () => ({
        searchItem: [
            {name:'전체', value:0}, 
            {name:'제목', value:1}, 
            {name:'내용', value:2}, 
            {name:'장소', value:3}],
        searchSeletedItem: '', // 검색할 값의 분류
        searchInput:'', // 검색 입력값
        list: [{
            seq:'1',
            title: '서울대 전시회',
            startDate: '2022-06-01',
            endDate: '2022-07-15',
            location:'서울시',
            imgSrc: '../assets/',
        },{
            seq:'1',
            title: '서울대 전시회',
            startDate: '2022-06-01',
            endDate: '2022-07-15',
            location:'서울시',
            imgSrc: '../assets/',
        },{
            seq:'1',
            title: '서울대 전시회',
            startDate: '2022-06-01',
            endDate: '2022-07-15',
            location:'서울시',
            imgSrc: '../assets/',
        },{
            seq:'1',
            title: '서울대 전시회',
            startDate: '2022-06-01',
            endDate: '2022-07-15',
            location:'서울시',
            imgSrc: '../assets/',
        },],
    }),
    methods:{
        searchByInput: function() {
            var param = {
                params:{
                    kind: this.$route.query.kind,
                    item: this.searchSeletedItem,
                    input: this.searchInput
                }
            }
            console.log(param);
            Axios.get("http://localhost:8080/exhibit", param).then(response =>{
                console.log(response);
            })
            this.list = [{
            seq:'1',
            title: '경북대 전시회',
            startDate: '2022-06-01',
            endDate: '2022-07-15',
            location:'대구시',
            imgSrc: '../assets/',
        },{
            seq:'2',
            title: '경북대 전시회',
            startDate: '2022-06-01',
            endDate: '2022-07-15',
            location:'대구시',
            imgSrc: '../assets/',
        },{
            seq:'3',
            title: '경북대 전시회',
            startDate: '2022-06-01',
            endDate: '2022-07-15',
            location:'대구시',
            imgSrc: '../assets/',
        },]
        }
    },
}
</script>

<style>

</style>