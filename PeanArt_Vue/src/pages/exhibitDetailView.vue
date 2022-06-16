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
                                    <v-img src="@/assets/exhib (1).png" width="800" :aspect-ratio="1/1" contain></v-img>
                                </v-col>
                                <v-col md="6" class="d-flex align-center">
                                    <v-card
                                    class="overflow-y-auto mx-auto"
                                    max-height="800">
                                        <v-card-title>{{this.list.exhibTitle}}</v-card-title>
                                        <v-card-text class="text-subtitle-1">{{this.list.exhibDetailInfo}}</v-card-text>
                                        <v-divider></v-divider>
                                        <v-card-text>전시 장소: {{this.list.exhibLocation}}</v-card-text>
                                        <v-card-text>전시회 홈페이지 : {{this.list.exhibUri}}</v-card-text>
                                        <v-card-text>전시 기간 : {{this.list.exhibStartDate}} ~ {{this.list.exhibEndDate}}</v-card-text>
                                    </v-card>
                                </v-col>
                            </v-row>
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
                        <v-col md="2" v-for="(item, index) in imgList" v-bind:key="index" align-self="center">
                            <v-img :src="getImgUrl(item)" max-height="200px" max-width="200px" contain></v-img>
                        </v-col>
                        <v-col md="4">
                            <v-card>
                                <v-container>
                                    <v-avatar size="100px">
                                        <v-img src="@/assets/Intersect.png" max-width="100px"></v-img>
                                    </v-avatar>
                                    <v-card-title>전시회 게시자</v-card-title>
                                    <v-card-actions class="justify-space-between">
                                        <v-btn>팔로우 하기</v-btn>
                                        <v-btn>정보 더보기</v-btn>
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
                                        <v-img :src="getImgUrl('Intersect.png')"></v-img>
                                    </v-avatar>
                                </v-col>
                                <v-col md="2">
                                    <v-card-title>{{this.user.nickname}}</v-card-title>
                                </v-col>
                                <v-col md="6">
                                    <v-textarea
                                    placeholder="리뷰를 남겨주세요."
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
            <v-row>
                
            </v-row>
        </v-container>
        <Foot_barVue></Foot_barVue>
    </v-main>
  </v-app>
</template>

<script>
import nav_bar from '@/components/nav_bar.vue'
import Foot_barVue from '@/components/foot_bar.vue'
export default {
    name: "exhibDetailView",
    components: {
    nav_bar,
    Foot_barVue
},
    data: ()=>({
        reviewContent:'',
        reviewCreDate:'',
        list: {
            exhibTitle:'전시 이름 : 경계없이 노닐다',
            exhibDetailInfo:'복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다. 그런 차이가 세상을 풍성하게, 좀 더 발전하게 만든다.복잡한 현대 사회 만큼 사람들의 개성은 다양하고 입체적이다. 그래서 같은 의미를 두고도 사람들은 각자 다른 해석을 하기도 한다',
            exhibLocation:'대구 중구 동내로 70',
            exhibUri:'http://homepage.com',
            exhibStartDate:'2022. 08. 18',
            exhibEndDate:'2022. 08. 30',
        },
        imgList: [
            'illustration.png',
            'illustration-1.png',
            'illustration-2.png',
            'illustration-3.png',    
        ],
        user: {
            'nickname': '리뷰 작성자'
        }
    }),
    methods: {
        getImgUrl(img){
            return require('@/assets/'+img)
        },
    }
}
</script>

<style>

</style>