<template>
    <v-app>
        <nav_bar></nav_bar>
        <v-container>
            <v-row>
                <v-col>
                    <h1>{{usrNickname}} 님 반갑습니다.</h1>
                </v-col>
            </v-row>
            <v-row>
                <v-col>
                    {{usrId}}
                </v-col>
            </v-row>
            <v-row>
                <v-col md="4">
                    <v-img
                    src="../assets/ai_1.png">
                    </v-img>
                </v-col>
                <v-col md="8">
                    {{usrName}}
                    {{usrPhone}}
                    {{usrAddress}}
                </v-col>
            </v-row>
            <v-divider class="mt-8"></v-divider>
        </v-container>
    </v-app>
</template>

<script>
import nav_bar from '@/components/nav_bar'
export default {
    name: 'exhibitRegisterView',
    components: {
        nav_bar,
    },
    data: () => ({
        usrNickname: '',
        usrId: '',
        usrName: '',
        usrPhone: '',
        usrAddress: '',
    }),
    methods:{
        
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