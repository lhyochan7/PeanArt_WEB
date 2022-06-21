<template>
  <v-app>
    <nav_bar></nav_bar>
    <v-main>
        <v-container fluid v-if="isLoaded == false">
        <v-skeleton-loader
            class="mx-auto"
            type="card"
            height="100%"
            width="100%"
            ></v-skeleton-loader>
        </v-container>
        <v-container fluid v-else>
            <v-row justify="center">
                <v-col md="10" class="mt-8">
                    <v-card class="d-flex mx-auto grey lighten-5">
                        <v-container>
                            <v-row>
                                <v-col md="6" class="d-flex align-center" align="center">
                                    <v-img :src="getImgUrl(this.exhib.fileDirName +'/'+ this.exhib.fileName)" width="800" :aspect-ratio="1/1"></v-img>
                                </v-col>
                                <v-col md="6" class="d-flex align-center">
                                    <v-card
                                    class="overflow-y-auto mx-auto"
                                    max-height="568"
                                    height="100%">
                                        <v-card-title class="text-h4">{{this.exhib.exhibTitle}}</v-card-title>
                                        <v-card-text class="text-h6">{{this.exhib.exhibSimpleExp}}</v-card-text>
                                        <v-card-text class="text-subtitle-1">{{this.exhib.exhibDetailExp}}</v-card-text>
                                        <v-divider></v-divider>
                                        <v-card-text class="text-subtitle-1">전시 장소: {{this.exhib.exhibLocation}}</v-card-text>
                                        <v-card-text class="text-subtitle-1">전시회 홈페이지 : <a :href="this.exhib.exhibUri" style="text-decoration:none;color:black;">{{this.exhib.exhibUri}}</a></v-card-text>
                                        <v-card-text class="text-subtitle-1">전시 기간 : {{this.exhib.exhibStartDate}} ~ {{this.exhib.exhibEndDate}}</v-card-text>
                                    </v-card>
                                </v-col>
                            </v-row>
                            <v-card-actions v-if="this.userId === this.userInfo.usrId || this.isAdmin">
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
                            <v-img :src="getImgUrl(item.fileDirName +'/'+ item.fileUuid)" max-height="200px" max-width="200px" contain></v-img>
                        </v-col>
                        <v-spacer></v-spacer>
                        <v-col md="2">
                            <v-card outlined>
                                <v-container>
                                    <v-avatar size="100px">
                                        <v-img :src="getImgUrl(this.exhibUserInfo.fileDirName +'/'+ this.exhibUserInfo.fileName)" max-width="100px"></v-img>
                                    </v-avatar>
                                    <v-card-title>{{this.exhibUserInfo.usrNickname}}</v-card-title>
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
                                        <v-img :src="getImgUrl(this.userInfo.fileDirName +'/'+ this.userInfo.fileName)"></v-img>
                                    </v-avatar>
                                </v-col>
                                <v-col md="2">
                                    <v-card-title>{{this.userInfo.usrNickname}}</v-card-title>
                                </v-col>
                                <v-col md="6">
                                    <v-form ref="reviewForm">
                                        <v-textarea
                                        placeholder="리뷰를 남겨주세요."
                                        v-model="reviewInput"
                                        :rules="[rules.review]"
                                        rows="3"
                                        no-resize
                                        outlined></v-textarea>
                                    </v-form>
                                </v-col>
                                <v-col md="2">
                                    <v-btn text outlined x-large class="grey darken-3 white--text" @click="registerReview()">등록</v-btn>
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
        isAdmin:false,
        // 입력받은 리뷰 데이터 저장용 Variable
        reviewInput:'',
        exhibSeq:'',
        // 불러온 전시회 정보
        exhib: {
            "exhibTitle": "KUCD Graduation Exhibition",
            "exhibKind": "1",
            "exhibTheme": "낭만주의,인상주의",
            "exhibSimpleExp": "건국대학교 예술디자인대학 커뮤니케이션디자인학과는 디자인분야를 바탕으로 인문사회 및 인접 분야와의 활발한 교류를 유도할 수 있는 학제간의 융합",
            "exhibDetailExp": "건국대학교 예술디자인대학 커뮤니케이션디자인학과는 디자인분야를 바탕으로 인문사회 및 인접 분야와의 활발한 교류를 유도할 수 있는 학제간의 융합을 통하여 사고와 정보를 통합하는 공감각적 창조성을 배양하고, 나아가 새로운 콘텐츠 개발 및 개별 영역들의 한계를 뛰어 넘어 커뮤니케이션을 할 수 있는 국제적 경쟁력을 지닌 커뮤니케이션디자인 전문가 양성을 궁극적인 목표로 삼고 있다. 이를 위해 인∙문화∙기술을 포함하는 다양한 분야의 폭넓은 이해와 함께, 미래의 사회∙문화적 흐름과 요구 등 환경변화에 적극적으로 대처할 수 있도록 각종 미디어를 이용한 통합적 커뮤니케이션디자인의 이론 교육과 실습을 수행한다. 이를 통해 창조적인 사고능력과 혁신적 문제해결 능력을 기반으로 하는 창의적 전문성을 갖춘 유능한 커뮤니케이션디자이너를 양성하기 위한 교육과정을 제공하고 있다",
            "exhibStartDate": "2022-06-21 00:00:00",
            "exhibEndDate": "2022-06-23 00:00:00",
            "exhibLocation": "경기 가평군 가평읍 석봉로 50",
            "exhibUri": "http://kucd.net/graduate/2017/",
            "usrSeq": 2,
            "exhibCretDate": "2022-06-20 11:24:58",
            "exhibModDate": "2022-06-20 11:24:58",
            "fileDirName": "12eef4ba-7d97-4ad5-8036-8608e43d5e9b",
            "fileName": "12eef4ba-7d97-4ad5-8036-8608e43d5e9b_poster.jpg",
            "goodsAllow": "1"
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
                revModDate: "2022-06-16 14:39:45",
                usrNickname: "admin",
                fileDirName: "99497c6d-75a7-4fec-b29f-9d1b16bab6c7_admin@admin.com",
                fileName: "c24f7dc2-5410-40fe-89bc-65af51901205_money.png"
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
        //
        rules:{
            review: value => (value.length == 0 || value.length < 10000) || '리뷰는 10000자 이내로 입력해야 합니다.'
        },
        // 수정시 prop으로 전달할 데이터
        exhibData: {},
        // 로딩완료 검사
        isLoaded: false,
    }),
    methods: {
        getImgUrl(img){
            return this.$Url+'imagePath/'+img.replace("PNG", "png")
        },
        deleteExhib() {
            const param ={params:{exhibSeq:this.$route.params.id}}
            axios.get(this.$Url+'exhibDelete',param).then(response =>{
                console.log(response);
                if(response.status==200){
                    axios.get("http://15.164.142.253:5000/updateModel").then(response=>{console.log(response)})
                    alert('삭제에 성공했습니다.')
                    this.$router.push('/exhib/list?kind=0');
                } else{
                    alert('삭제에 실패했습니다.')
                }
            })
        },
        toModify() {
            this.exhibData = {
                exhibTheme:this.exhib.exhibTheme,
                startDate:this.exhib.exhibStartDate,
                endDate:this.exhib.exhibEndDate,
                fileName:this.exhib.fileName,
                fileDirName:this.exhib.fileDirName,
                fileList:this.fileList,
                exhibTitle:this.exhib.exhibTitle,
                exhibLocation:this.exhib.exhibLocation,
                exhibUri:this.exhib.exhibUri,
                exhibSimpleInfo:this.exhib.exhibSimpleExp,
                exhibDetailInfo:this.exhib.exhibDetailExp,
                exhibKind:this.exhib.exhibKind,
                exhibGoodsAllow:this.exhib.goodsAllow,
                usrSeq: this.exhibUserInfo.usrSeq
            }
            const routet = {
                name: 'exhibitRegisterView',
                params:{
                    exhibData:this.exhibData 
                },
            }
            this.$router.push(routet)
        },
        registerReview() {
            if(this.$refs.reviewForm.validate()){
                const frm = new FormData()
                frm.append('revContent',this.reviewInput)
                axios.post(this.$Url+'reviewRegister', frm).then(response => {
                    console.log(response)
                    if(response.status==200){
                        var param = {params:{
                            exhibSeq: this.$route.params.id
                        }}
                        axios.get(this.$Url+'detail',param).then(response=>{
                            console.log(response);
                            if(response.status==200){
                                this.userInfo = response.data.userInfo;
                                this.reviewList = response.data.reviewList;
                                this.exhib = response.data.exhib;
                                this.fileList = response.data.fileList;
                            }
                        })
                    } else{
                        alert('리뷰 등록에 실패했습니다. 다시 시도해주세요.')
                    }
                })
            }
            axios.get
        },
    },
    mounted() {
        axios.get('/sessionCheck').then(response=>{
            if(response.status==200){
                if(sessionStorage.getItem("usdId") != response.data){
                    // 세션 로그인한 상태 + [세션스토리지 아이디 = 서버 세션 아이디] 인 상황
                    console.log()
                } else{
                    alert('비정상적인 로그인 상태입니다.')
                    sessionStorage.removeItem("usrId")
                    this.$router.push('/main')
                }
            }else{
                alert('비정상적인 로그인 상태입니다.')
                sessionStorage.removeItem("usrId")
                this.$router.push('/main')
            }
        })
        this.userInfo = {}
this.userId = sessionStorage.getItem("usdId")
        if(this.userId != null){
            axios.get(this.$Url+'my-page').then(response=>{
                console.log(response);
                if(response.status==200){
                    this.userInfo = response.data.userInfo;
                    const param = {params:{usrId:this.userInfo.usrId}}
                    axios.get(this.$Url+'adminCheck', param).then(response=>{
                        console.log(response)
                        if(response.status==200){
                            this.isAdmin = true;
                        }
                    })
                } else{
                    this.userInfo = {}
                }
            })
            var param = {params:{
                exhibSeq: this.$route.params.id
            }}
        }
        
        axios.get(this.$Url+'detail',param).then(response=>{
            console.log(response);
            if(response.status==200){
                this.exhibUserInfo = response.data.userInfo;
                this.reviewList = response.data.reviewList;
                this.exhib = response.data.exhib;
                this.fileList = response.data.fileList;
                this.exhibSeq = this.fileList[0].exhibSeq
                this.isLoaded = true
            }
        })
    }
}
</script>

<style>

</style>
