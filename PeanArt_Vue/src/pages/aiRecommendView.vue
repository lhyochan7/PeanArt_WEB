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
                                                    <v-img :src="getImgUrlNotPrefix('AI_images/'+n+'.png')" max-width="150" max-height="150">
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
            <v-row justify="center" class="mt-16">
                <v-col md="10">
                    당신의 취향에 맞는 전시회는...
                    <v-card>
                        <Exhib_card :exhibData="mainExhib"></Exhib_card>
                        <Exhib_card :exhibData="mainExhib"></Exhib_card>
                        <Exhib_card :exhibData="mainExhib"></Exhib_card>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import nav_bar from '@/components/nav_bar.vue'
import axios from 'axios';
import Exhib_card from '../components/exhib_card.vue';
export default {
    name:'aiRecommend',
    components:{
    nav_bar,
    Exhib_card
},
    data: ()=>({
        imageForAI:[],
        items: ['디자인', '사진', '모더니즘', '바로크', '모던한', '표현주의', '심플', '고전주의', '변화의', '비판적', '입체파', '밝은', '어두운', '형식주의', '이상주의', '작품'],
        selectedPicture:[],
        selectedTheme: [],
        // step 이동용
        step:1,
        // 추천 받은 전시회 목록
        exhibList:[],
        //
        mainExhib: {
        "exhibSeq": 9,
        "exhibTitle": "seoul_design",
        "exhibSimpleExp": "seoul_design",
        "exhibStartDate": "2020-10-10 01:01:01",
        "exhibEndDate": "2020-10-10 01:01:01",
        "exhibLocation": "seoul_design",
        "fileDirName": "63c9e7d8-9507-489c-89d1-a30718cf8cc3_seoul_design",
        "fileName": "63c9e7d8-9507-489c-89d1-a30718cf8cc3_poster.PNG"
    }
    }),
    methods:{
      remove (item) {
        this.selectedTheme.splice(this.selectedTheme.indexOf(item), 1)
        this.selectedTheme = [...this.selectedTheme]
      },
      pictureSelect(){
        return (this.selectedPicture.length < 4 && this.selectedPicture.length > 0) || '작품은 1개 이상 선택 해야하며, 3개 이하로 선택 가능합니다.'
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
            const idx2 = this.selectedPicture.map(e=>{
                e = e + 1;
                return e;
            });
            var param = {
                params:{
                    idx: idx2.join(','),
                    keywords: this.selectedTheme.join(',')
                }
            }
            axios.get('http://15.164.142.253:8080/AIpage', param, {headers: {'content-Type': "application/json; charset=UTF-8",
                'Allow-Control-Allow-Origin': '*'
            }}).then(response=>{
                console.log(response)
                if(response.status==200){
                    response.data
                    // exhibList에 서버로 부터 받은 array 중복제거 후 할당
                    // 이후 그 안의 값 하나씩? 해서 exhib정보 불러오기
                }
            })
        }
      },
        getImgUrlNotPrefix(pic){
            return this.$Url+'imagePath/' + pic.replace('PNG', 'png')
        }
    },
    mounted(){
        axios.get('')
    },
}
</script>

<style>

</style>