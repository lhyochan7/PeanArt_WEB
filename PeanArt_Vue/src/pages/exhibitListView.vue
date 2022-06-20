<template>
  <v-app>
      <nav_bar></nav_bar>
      <v-container fill-height>
        <v-row no-gutters alignt-self="start">
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
                <v-btn outlined x-large @click="searchByInput()" height="56">검색</v-btn>
            </v-col>
        </v-row>
        <v-row>
            <v-col md="12" v-if="isLoaded == false">
                <v-skeleton-loader
                class="mx-auto"
                type="card"
                height="100%"
                width="100%"
                ></v-skeleton-loader>
            </v-col>
            <v-col class="d-flex child-flex" cols="4" v-else v-for="(item, i) in list" v-bind:key="i">
                <exhib_card :exhibData="item"></exhib_card>
            </v-col>
        </v-row>
      </v-container>
    <Foot_bar/>
  </v-app>
</template>

<script>
import exhib_card from '../components/exhib_card.vue'
import nav_bar from '../components/nav_bar.vue'
import axios from 'axios'
import Foot_bar from '@/components/foot_bar.vue';
export default {
    name: 'exhibitListView',
    components: { nav_bar, exhib_card, Foot_bar },
    data: () => ({
        searchItem: [
            {name:'전체', value:0}, 
            {name:'제목', value:1}, 
            {name:'내용', value:2}, 
            {name:'장소', value:3},],
        searchSeletedItem: '', // 검색할 값의 분류
        searchInput:'', // 검색 입력값
        list: [
    {
        "exhibSeq": 12,
        "exhibTitle": "KUCD Graduation Exhibition",
        "exhibSimpleExp": "건국대학교 예술디자인대학 커뮤니케이션디자인학과는 디자인분야를 바탕으로 인문사회 및 인접 분야와의 활발한 교류를 유도할 수 있는 학제간의 융합",
        "exhibStartDate": "2022-06-21 00:00:00",
        "exhibEndDate": "2022-06-23 00:00:00",
        "exhibLocation": "경기 가평군 가평읍 석봉로 50",
        "fileDirName": "12eef4ba-7d97-4ad5-8036-8608e43d5e9b",
        "fileName": "12eef4ba-7d97-4ad5-8036-8608e43d5e9b_poster.jpg"
    },
    {
        "exhibSeq": 13,
        "exhibTitle": "RenaissanceS",
        "exhibSimpleExp": "2019년 발 드 루아르(Val de Loire) 지역에 르네상스의 물결이 넘칠 예정이다.",
        "exhibStartDate": "2022-06-21 00:00:00",
        "exhibEndDate": "2022-06-23 00:00:00",
        "exhibLocation": "부산 강서구 르노삼성대로 14",
        "fileDirName": "29db1a3e-2ca7-46fa-a33d-10ccb543153f",
        "fileName": "29db1a3e-2ca7-46fa-a33d-10ccb543153f_poster.jpg"
    },
    {
        "exhibSeq": 14,
        "exhibTitle": "asia realizm",
        "exhibSimpleExp": "싱가포르국립미술관과 공동 기획하여 양국의 국립미술관을 순회하는 <아시아 리얼리즘>전은, 아시아 10개국의 근대미술 명화 104점을 한국에 최초로 소개하는 전시입니다.",
        "exhibStartDate": "2022-07-07 00:00:00",
        "exhibEndDate": "2022-07-21 00:00:00",
        "exhibLocation": "대구 수성구 미술관로 40",
        "fileDirName": "343ca51d-8c1c-4375-bd1e-b2476675d0ee",
        "fileName": "343ca51d-8c1c-4375-bd1e-b2476675d0ee_poster.jpg"
    },
    {
        "exhibSeq": 15,
        "exhibTitle": "ultural issues. Exhibition E",
        "exhibSimpleExp": "총 10개의 반이 반별로 기획한 이번 전시는 수업의 성격에 따라 네 개의 파트로 나뉜다. 전시 P(Practice)에서는 기업적인 성향을 띄는 작업을 보여준다. ",
        "exhibStartDate": "2022-08-07 00:00:00",
        "exhibEndDate": "2022-08-25 00:00:00",
        "exhibLocation": "서울 마포구 신촌로2길 5-18",
        "fileDirName": "66b1aec8-9381-4953-a828-aa569ee1f6ec",
        "fileName": "66b1aec8-9381-4953-a828-aa569ee1f6ec_poster.jpg"
    },
    {
        "exhibSeq": 16,
        "exhibTitle": "Anonymous Society of Painters, Sculptors, Engravers",
        "exhibSimpleExp": "이 쇼에서는 18 명의 화가가 총 241개 작품을 선보임",
        "exhibStartDate": "2022-06-02 00:00:00",
        "exhibEndDate": "2022-06-16 00:00:00",
        "exhibLocation": "경북 칠곡군 왜관읍 현대로 15",
        "fileDirName": "6f45980b-4868-4166-8b05-82b22b043341",
        "fileName": "6f45980b-4868-4166-8b05-82b22b043341_poster.jpg"
    },
    {
        "exhibSeq": 17,
        "exhibTitle": "seoul National univ Fine Arts",
        "exhibSimpleExp": "평범한 삶을 바꾼 큰 변화 이후, 우리는 단절의 경험을 공유하고 있습니다. 거리 두기가 새로운 기준이 된 일상에서 다시 사회를 연결하려는 우리의 진취적인 태도가 만들어졌습니다",
        "exhibStartDate": "2022-08-01 00:00:00",
        "exhibEndDate": "2022-08-19 00:00:00",
        "exhibLocation": "서울 강남구 논현로 848",
        "fileDirName": "e8903b7b-4bd2-4e55-8f72-8e7f7275af0d",
        "fileName": "e8903b7b-4bd2-4e55-8f72-8e7f7275af0d_poster.jpg"
    },
    {
        "exhibSeq": 18,
        "exhibTitle": "HI, POP !",
        "exhibSimpleExp": "거리로 나온 예술, 팝아트 展\n국내 최대규모 팝아트 전시를 만나보세요",
        "exhibStartDate": "2022-06-24 00:00:00",
        "exhibEndDate": "2022-06-25 00:00:00",
        "exhibLocation": "서울 강남구 가로수길 5",
        "fileDirName": "8c46358c-4704-483f-a871-a3ff038c3b07",
        "fileName": "8c46358c-4704-483f-a871-a3ff038c3b07_poster.jpg"
    }],
        isLoaded: false
    }),
    methods:{
        searchByInput: function() {
            var param;
            if (this.searchSeletedItem != '' && this.searchInput != ''){
                param = {
                    params:{
                        kind: this.$route.query.kind,
                        item: this.searchSeletedItem,
                        searchTxt: this.searchInput
                    }
                }
            } else{
                param = {
                    params:{
                        kind: this.$route.query.kind,
                    }
                }
            }
            console.log(param);
            axios.get(this.$Url+"search", param,{headers: {
                'Allow-Control-Allow-Origin': '*'
            }}).then(response =>{
                console.log(response);
                this.list = response.data;
                this.isLoaded = true
            })
        },
    },
    /*mounted(){
        var param = {
            params:{
                kind: this.$route.query.kind
            }
        }
        axios.get('http://15.164.142.253:8080/search', param).then(response =>{
            console.log(response)
            this.list = response.data
        })
    }*/
    created() {
        this.$watch(
        () => this.$route.query,
        () => {
            this.searchByInput()
        }
    )
    },
    mounted() {
        this.searchSeletedItem = this.$route.query.item!=undefined ? this.$route.query.item : ''
        this.searchInput = this.$route.query.searchTxt!=undefined ? this.$route.query.searchTxt : ''
        this.searchByInput()
    }
}
</script>

<style>

</style>