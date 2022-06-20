<template>
    <v-app>
        <nav_bar></nav_bar>
        <v-container fluid justify-center>
            <v-row justify="center" class="mt-16" align-self="center">
                <v-col md="10" class="text-center">
                    <div class="text-h5 mb-8 font-weight-bold" v-if="step==1">
                        마음에 드는 작품을 골라주세요
                    </div>
                    <div class="text-h5 mb-8 font-weight-bold" v-if="step==2">
                        선호하는 전시 주제를 골라주세요
                    </div>
                    <template>
                        <v-sheet
                            class="mx-auto"
                            elevation="8"
                            max-width="1000"
                        >
                            <v-window v-model="step">
                                <v-window-item :value="1">
                                    <v-slide-group
                                    v-model="selectedPicture"
                                    class="pa-4"
                                    multiple
                                    show-arrows
                                    >
                                        <v-slide-item
                                            v-for="n in 12"
                                            :key="n"
                                            v-slot="{ active, toggle }"
                                        >
                                            <v-card
                                            :color="active ? 'primary' : 'grey lighten-5'"
                                            class="ma-4"
                                            height="200"
                                            width="200"
                                            @click="toggle"
                                            >
                                                <v-row
                                                    class="fill-height"
                                                    align="center"
                                                    justify="center"
                                                >
                                                    <v-img :src="require('@/assets/ai_1.png')" max-width="150" max-height="150">
                                                        <v-scale-transition>
                                                        <v-icon
                                                            v-if="active"
                                                            color="white"
                                                            size="48"
                                                            v-text="'mdi-close-circle-outline'"
                                                        ></v-icon>
                                                        </v-scale-transition>
                                                    </v-img>
                                                </v-row>
                                            </v-card>
                                        </v-slide-item>
                                    </v-slide-group>
                                    <v-form ref="firstForm">
                                        <div class="ml-8">
                                            <v-input
                                            error
                                            v-model="selectedPicture"
                                            :rules="[pictureSelect]"
                                            />
                                        </div>
                                    </v-form>
                                </v-window-item>
                                <v-window-item :value="2">
                                    <v-container>
                                        <v-row class="justify-center">
                                            <v-col md="8">
                                                <template>
                                                <v-select
                                                    v-model="selectedTheme"
                                                    :items="items"
                                                    chips
                                                    clearable
                                                    label="전시회 테마"
                                                    multiple
                                                    solo
                                                    :rules="[themeSelect]"
                                                >
                                                    <template v-slot:selection="{ attrs, item, select, selected }">
                                                    <v-chip
                                                        v-bind="attrs"
                                                        :input-value="selected"
                                                        close
                                                        @click="select"
                                                        @click:close="remove(item)"
                                                    >
                                                        <strong>{{ item }}</strong>&nbsp;
                                                    </v-chip>
                                                    </template>
                                                </v-select>
                                                </template>
                                            </v-col>
                                        </v-row>
                                    </v-container>
                                </v-window-item>
                        </v-window>
                        <v-card-actions class="justify-space-between">
                            <v-btn v-if="step==2" @click="step==2 ? step = 1 : step = 2">이전</v-btn>
                            <v-spacer></v-spacer>
                            <v-btn @click="nextStep()">다음</v-btn>
                        </v-card-actions>
                        </v-sheet>
                        </template>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import nav_bar from '@/components/nav_bar.vue'
import axios from 'axios';
export default {
    name:'aiRecommend',
    components:{
        nav_bar
    },
    data: ()=>({
        imageForAI:[],
        items: ['디자인', '서양화', '사진', '현대미술', '미디어아트', '풍경화', '모던한', '유화', '심플', '세련된', '변화의', '비판적', '멀티컬러', '밝은', '어두운', '추상화'],
        selectedPicture:[],
        selectedTheme: [],
        // step 이동용
        step:1,
        // 추천 받은 전시회 목록
        exhibList:[]
    }),
    methods:{
      remove (item) {
        this.selectedTheme.splice(this.selectedTheme.indexOf(item), 1)
        this.selectedTheme = [...this.selectedTheme]
      },
      pictureSelect(){
        return (this.selectedPicture.length < 3 && this.selectedPicture.length > 0) || '작품은 1개 이상 선택 해야하며, 3개 이하로 선택 가능합니다.'
      },
      themeSelect() {
        return (this.selectedTheme.length < 6 && this.selectedTheme.length > 0 ) || '주제는 1개 이상 선택 해야하며, 5개 이하로 선택 가능합니다.';
      },
      nextStep(){
        if(this.step==1){
            if(this.$refs.firstForm.validate()){
                this.step = 2
            }
        } else{
            var param = {
                params:{
                    idx: this.selectedPicture,
                    keywords: this.selectedTheme
                }
            }
            axios.get('/AIpage', param).then(response=>{
                console.log(response)
                if(response.status==200){
                    response.data.forEach
                }
            })
        }
      }
    },
    mounted(){
        axios.get('')
    },
    getImgUrlNotPrefix(pic){
        return 'http://15.164.142.253:8080/imagePath/' + pic.replace('PNG', 'png')
    }
}
</script>

<style>

</style>