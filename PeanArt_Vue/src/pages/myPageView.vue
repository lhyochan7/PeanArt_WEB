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
                        src="../assets/ai_1.png"
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
                        max-width="800"
                    >
                        <v-slide-group
                        class="pa-4"
                        show-arrows
                        >
                            <v-slide-item
                                v-for="(item, i) in visitedExhib"
                                :key="i"
                            >
                                <v-card
                                class="ma-4"
                                height="300"
                                width="200"
                                >
                                <v-img :src="getImgUrl(item.imgSrc)" height="200px" contain></v-img>
                                <v-card-text>{{item.name}}</v-card-text>
                                </v-card>
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
                        max-width="800"
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
                                height="300"
                                width="200"
                                elevation="0"
                                outlined
                                >
                                <v-img :src="getImgUrl(item.imgSrc)" max-height="200px" max-width="200px" contain></v-img>
                                <v-card-text>{{item.name}}</v-card-text>
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
        // 방문한 전시회 목록용 list
        visitedExhib: [
            {
                name: '청주대학교 예술대학 시각디자인 전공 제 41회 졸업 전시회',
                imgSrc: 'exhib (1).png',
            },{
                name: '2021 국민대학교 공업디자인학과 졸업 전시회',
                imgSrc: 'exhib (2).png',
            },{
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (3).png',
            },{
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },{
                name: '2020 계명대학교 시각디자인과 졸업 전시',
                imgSrc: 'exhib (1).png',
            },{
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
            return require('@/assets/' + pic)
        }
    },
    mounted(){
        this.usrNickname= 'nickname'
        this.usrId= 'example@exam.com'
        this.usrName= '홍길동'
        this.usrPhone= '010-1234-5678'
        this.usrAddress= '대구광역시 동구 동내로'
    }
}
</script>

<style>

</style>