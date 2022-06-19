<template>
    <v-app>
        <nav_bar></nav_bar>
        <v-main>
            <v-container>
                <v-row>
                    <v-col>
                        <h1>{{usrNickname}} 님 반갑습니다.</h1>
                        <div class="text-caption">{{usrId}}</div>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col md="4">
                        <v-img
                        :src="getImgUrlNotPrefix(this.usrProfile)"
                        height="300px"
                        width="300px"
                        contain>
                        </v-img>
                    </v-col>
                    <v-col md="8" align-self="center">
                        <v-row>
                            <v-col md="2">
                                <p class="text-subtitle-1">이름</p>
                            </v-col>
                            <v-col class="offset-md-1">
                                {{usrName}}
                            </v-col>
                        </v-row>
                        <v-row class="mt-n8">
                            <v-col md="2">
                                <p class="text-subtitle-1">전화번호</p>
                            </v-col>
                            <v-col class="offset-md-1">
                                {{usrPhone}}
                            </v-col>
                        </v-row>
                        <v-row class="mt-n8">
                            <v-col md="2">
                                <p class="text-subtitle-1">주소</p>
                            </v-col>
                            <v-col class="offset-md-1">
                                {{usrAddress}}
                            </v-col>
                        </v-row>
                        <v-row justify="end">
                            <v-btn color="green">회원정보 수정</v-btn>
                        </v-row>
                    </v-col>
                </v-row>
                <v-row>
                    <v-divider></v-divider>
                </v-row>
                <v-row>
                    <div class="text-h6 font-weight-bold ml-4 mt-4">방문한 전시회 목록</div>
                </v-row>
                <v-row class="mt-8">
                    <v-sheet
                        class="mx-auto"
                        elevation="1"
                        max-width="1000"
                    >
                        <v-slide-group
                        class="pa-4"
                        show-arrows
                        >
                            <v-slide-item
                                v-for="(item, i) in visitedExhib"
                                :key="i"
                            >
                                <router-link style="text-decoration:none; color:black;" :to="`/exhib/${item.exhibSeq}/detail`">
                                    <v-card
                                    class="ma-4"
                                    height="300"
                                    width="200"
                                    >
                                        <v-img :src="getImgUrlNotPrefix(item.exhibPosterUrl)" height="200px" contain></v-img>
                                        <v-card-text>{{item.exhibTitle}}</v-card-text>
                                    </v-card>
                                </router-link>
                            </v-slide-item>
                        </v-slide-group>
                    </v-sheet>
                </v-row>
                <v-row class="mt-8">
                    <v-divider></v-divider>
                </v-row>
                <v-row>
                    <div class="text-h6 font-weight-bold ml-4 mt-4">팔로우한 유저 목록</div>
                </v-row>
                <v-row class="mt-8">
                    <v-sheet
                        class="mx-auto"
                        elevation="1"
                        max-width="1000"
                    >
                        <v-slide-group
                        class="pa-4"
                        show-arrows
                        >
                            <v-slide-item
                                v-for="(item, i) in followedUser"
                                :key="i"
                            >
                                <v-card
                                class="ma-4"
                                height="200"
                                width="200"
                                elevation="0"
                                outlined
                                align="center"
                                >
                                    <v-container>
                                        <v-avatar size="100px"><v-img :src="getImgUrlNotPrefix(item.followedImgUrl)" max-height="200px" max-width="200px"></v-img></v-avatar>
                                        <v-card-text>{{item.usrNickname}}</v-card-text>
                                    </v-container>
                                </v-card>
                            </v-slide-item>
                        </v-slide-group>
                    </v-sheet>
                </v-row>
            </v-container>
        </v-main>
    </v-app>
</template>

<script>
import nav_bar from '@/components/nav_bar'
import axios from 'axios'
export default {
    name: 'myPageView',
    components: {
        nav_bar,
    },
    data: () => ({
        // 사용자 정보 표시용 Variable
        usrNickname: '',
        usrId: '',
        usrName: '',
        usrPhone: '',
        usrAddress: '',
        usrProfile:'',
        // 방문한 전시회 목록용 list
        visitedExhib: [
            {
                exhibSeq: 1,
                name: '청주대학교 예술대학 시각디자인 전공 제 41회 졸업 전시회',
                imgSrc: 'exhib (1).png',
            },{
                exhibSeq: 1,
                name: '2021 국민대학교 공업디자인학과 졸업 전시회',
                imgSrc: 'exhib (2).png',
            },{
                exhibSeq: 1,
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (3).png',
            },{
                exhibSeq: 1,
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },{
                exhibSeq: 1,
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },{
                exhibSeq: 1,
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            }
        ],
        // 팔로우한 유저 목록용 list
        followedUser: [
            {
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },
            {
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },
            {
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },
            {
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },
            {
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },
        ]
    }),
    methods:{
        getImgUrl(pic) {
            return require('C:/img/' + pic)
        },
        getImgUrlNotPrefix(pic){
            return pic.replace('PNG','png')
        }
    },
    mounted(){
        if(sessionStorage.getItem('usrId') == null){
            alert('로그인 해주세요.')
            this.$router.push('/login')
        }
        else{
            axios.get('/my-page').then(response=>{
                console.log(response)
                if(response.status != 200){
                    alert('에러가 발생했습니다.')
                    this.$router.push('/main')
                }
                const data = response.data
                this.usrNickname = data.userInfo.usrNickname
                this.usrId = data.userInfo.usrId
                this.usrName = data.userInfo.usrName
                this.usrPhone = data.userInfo.usrPhone
                this.usrAddress = data.userInfo.usrAdrs
                this.usrProfile = data.profileImg
                this.visitedExhib = data.exhibList
                this.followedUser = data.followList
                console.log(this.visitedExhib)
                console.log(this.followedUser)
            })
        }
    }
}
</script>

<style>

</style>