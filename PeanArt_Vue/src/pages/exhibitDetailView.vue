<template>
  <v-app>
    <nav_bar></nav_bar>
    <v-main>
        <v-container fluid>
            <v-row justify="center">
                <v-col md="10" class="mt-8">
                    <v-card class="d-flex mx-auto grey lighten-5">
                        <v-container>
                            <v-row>
                                <v-col md="6" class="d-flex align-center" align="center">
                                    <v-img :src="getImgUrl(this.exhib.fileDirName + this.exhib.fileName)" width="800" :aspect-ratio="1/1" contain></v-img>
                                </v-col>
                                <v-col md="6" class="d-flex align-center">
                                    <v-card
                                    class="overflow-y-auto mx-auto"
                                    max-height="800">
                                        <v-card-title>{{this.exhib.exhibTitle}}</v-card-title>
                                        <v-card-text class="text-subtitle-2">{{this.exhib.exhibSimpleExp}}</v-card-text>
                                        <v-card-text class="text-subtitle-1">{{this.exhib.exhibDetailExp}}</v-card-text>
                                        <v-divider></v-divider>
                                        <v-card-text>전시 장소: {{this.exhib.exhibLocation}}</v-card-text>
                                        <v-card-text>전시회 홈페이지 : {{this.exhib.exhibUri}}</v-card-text>
                                        <v-card-text>전시 기간 : {{this.exhib.exhibStartDate}} ~ {{this.exhib.exhibEndDate}}</v-card-text>
                                    </v-card>
                                </v-col>
                            </v-row>
                            <v-card-actions v-if="this.userId === this.userInfo.usrId">
                                <v-col class="text-right">
                                    <v-btn x-large class="mr-4" elevation="1" @click="toModify()">수정</v-btn>
                                    <v-btn x-large elevation="1" @click="deleteExhib()">삭제</v-btn>
                                </v-col>
                            </v-card-actions>
                        </v-container>
                    </v-card>
                </v-col>
            </v-row>
            <v-row justify="center" class="mt-8">
                <v-col md="11">
                    <v-divider></v-divider>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col md="10">
                    <h1>전시 작품</h1>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col md="10">
                    <v-row>                    
                        <v-col md="2" v-for="(item, index) in fileList" v-bind:key="index" align-self="center">
                            <v-img :src="getImgUrl(item.fileDirName + item.fileName)" max-height="200px" max-width="200px" contain></v-img>
                        </v-col>
                        <v-col md="4">
                            <v-card outlined>
                                <v-container>
                                    <v-avatar size="100px">
                                        <v-img :src="getImgUrl(this.userInfo.fileDirName + this.userInfo.fileName)" max-width="100px"></v-img>
                                    </v-avatar>
                                    <v-card-title>{{this.userInfo.usrNickname}}</v-card-title>
                                    <v-card-actions class="justify-space-around">
                                        <v-btn text color="red">팔로우 하기</v-btn>
                                        <v-btn text>정보 더보기</v-btn>
                                    </v-card-actions>
                                </v-container>
                            </v-card>
                        </v-col>
                    </v-row>

                </v-col>  
            </v-row>
            <v-row justify="center" class="my-4">
                <v-col md="11">
                    <v-divider></v-divider>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col md="10">
                    <h1>리뷰</h1>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col md="10">
                    <v-card class="grey lighten-5">
                        <v-container>
                            <v-row>
                                <v-col md="2" align="end">
                                    <v-avatar size="100px">
                                        <v-img :src="getImgUrl(this.userInfo.fileDirName + this.fileName)"></v-img>
                                    </v-avatar>
                                </v-col>
                                <v-col md="2">
                                    <v-card-title>{{this.userInfo.usrNickname}}</v-card-title>
                                </v-col>
                                <v-col md="6">
                                    <v-textarea
                                    placeholder="리뷰를 남겨주세요."
                                    v-model="reviewInput"
                                    rows="3"
                                    hide-details
                                    no-resize
                                    outlined></v-textarea>
                                </v-col>
                                <v-col md="2">
                                    <v-btn text outlined x-large class="grey darken-3 white--text">등록</v-btn>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-card>
                </v-col>
            </v-row>
            <v-row justify="center">
                <v-col md="11" class="mt-8">
                    <v-divider></v-divider>
                </v-col>
            </v-row>
            <v-row justify="center" v-for="(item, index) in reviewList" :key="index">
                <v-col md="10">
                    <reviewCard :reviewData="item"></reviewCard>
                </v-col>
            </v-row>
        </v-container>
        <Foot_barVue></Foot_barVue>
    </v-main>
  </v-app>
</template>

<script>
import nav_bar from '@/components/nav_bar.vue'
import Foot_barVue from '@/components/foot_bar.vue'
import reviewCard from '@/components/review_card.vue'
import axios from 'axios'
export default {
    name: "exhibDetailView",
    components: {
    nav_bar,
    Foot_barVue,
    reviewCard
},
    data: ()=>({
        // 현재 로그인한 사용자 정보 저장용 Variable
        userId:'',
        // 입력받은 리뷰 데이터 저장용 Variable
        reviewInput:'',
        // 불러온 전시회 정보
        exhib: {
            exhibTitle:'전시 이름 : 경계없이 노닐다',
            exhibDetailInfo:'복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다',
            exhibLocation:'대구 중구 동내로 70',
            exhibUri:'http://homepage.com',
            exhibStartDate:'2022. 08. 18',
            exhibEndDate:'2022. 08. 30',
            "usrSeq": 1,
            "exhibCretDate": "2022-06-16 05:15:47",
            "exhibModDate": "2022-06-16 05:15:47",
            "fileDirName": "f6d798bc-945e-405a-8e31-e61d14670166_dankook",
            "fileName": "f6d798bc-945e-405a-8e31-e61d14670166_poster.PNG",
            "goodsAllow": 1
        },
        // 불러온 전시회를 등록한 사용자
        exhibUserInfo: {
            usrSeq: 1,
            usrId: "admin@admin.com",
            usrPhone: "010-1234-5678",
            usrNickname: "admin",
            usrName: "admin",
            usrAdrs: "대구 동구 동내로 70",
            fileDirName: "99497c6d-75a7-4fec-b29f-9d1b16bab6c7_admin@admin.com",
            fileName: "c24f7dc2-5410-40fe-89bc-65af51901205_money.png"
        },
        // 불러온 전시회 리뷰 목록
        reviewList: [
            {
                revSeq: 1,
                usrSeq: 1,
                exhibSeq: 1,
                revContent: "cool exhibition review",
                revCretDate: "2022-06-16 14:39:45",
                revModDate: "2022-06-16 14:39:45"
            }
        ],
        // 불러온 전시회 사진 목록
        fileList: [
            {
                exhibSeq: 1,
                fileDirName: "f6d798bc-945e-405a-8e31-e61d14670166_dankook",
                fileName: "1.PNG",
                contentType: null,
                file_Uuid: "325c996c-ef7d-4468-86d2-702439621c57"
            },
            {
                exhibSeq: 1,
                fileDirName: "f6d798bc-945e-405a-8e31-e61d14670166_dankook",
                fileName: "2.PNG",
                contentType: null,
                file_Uuid: "c3d60092-02dc-46f5-944b-f658979b8f73"
            },
            {
                exhibSeq: 1,
                fileDirName: "f6d798bc-945e-405a-8e31-e61d14670166_dankook",
                fileName: "3.PNG",
                contentType: null,
                file_Uuid: "31ca7a4f-4005-4c9e-864a-27cbd29cd031"
            },
            {
                exhibSeq: 1,
                fileDirName: "f6d798bc-945e-405a-8e31-e61d14670166_dankook",
                fileName: "4.PNG",
                contentType: null,
                file_Uuid: "ab792463-7b1a-4dc2-84e7-14bbfca58494"
            }
        ],
        // 로그인한 사용자 정보
        userInfo:{
            usrSeq: 1,
            usrId: "admin@admin.com",
            usrPhone: "010-1234-5678",
            usrNickname: "admin",
            usrName: "admin",
            usrAdrs: "대구 동구 동내로 70",
            fileDirName: "99497c6d-75a7-4fec-b29f-9d1b16bab6c7_admin@admin.com",
            fileName: "c24f7dc2-5410-40fe-89bc-65af51901205_money.png"
        },
    }),
    methods: {
        getImgUrl(img){
            return require(img)
        },
        deleteExhib() {
            var param = { params:{
                exhibSeq:this.exhib.exhibSeq
            }}
            axios.get('http://localhost:8080/exhibDelete',param).then(response =>{
                console.log(response);
                if(response.status==200){
                    alert('삭제에 성공했습니다.')
                    this.$router.push('/exhib/list?kind=0');
                }
            })
        },
        toModify() {
            
        }
    },
    mounted() {
        this.userId = sessionStorage.getItem('usrId')
        axios.get('http://localhost:8080/my-page').then(response=>{
            console.log(response);
            if(response.status==200){
                this.userInfo = response.data.userInfo;
            }
        })
    }
}
</script>

<style>

</style>