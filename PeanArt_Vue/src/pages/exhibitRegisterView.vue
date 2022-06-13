<template>
  <v-app>
    <nav_bar></nav_bar>
    <v-container justify-center class="mt-16" grey lighten-5 fluid>
        <v-row>
            <v-col class="text-center">
                <h1>전시회 등록</h1>
            </v-col>
        </v-row>
        <v-row>
            <v-col class="text-center mt-n4">
                해당 전시회에 관한 정보들을 입력해주세요
            </v-col>
        </v-row>
        <v-expansion-panels flat>
            <v-expansion-panel>
                <v-expansion-panel-header>
                    <v-row class="justify-center">
                        <v-col md="8">
                            <div><v-icon>mdi-numeric-1-circle</v-icon> 기본 정보</div>
                            <div class="text-caption">*항목들은 필수로 입력해주세요</div>
                        </v-col>
                    </v-row>
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                        <v-row class="justify-center">
                            <v-col md="8">
                                <v-text-field label="*전시 이름" outlined></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="8">
                                <v-text-field label="*전시회 장소" outlined></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="8">
                                <v-text-field label="*전시회 홈페이지 주소" outlined></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="8">
                                <v-textarea label="*상세 설명" outlined auto-grow counter></v-textarea>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="4">
                                <v-menu
                                    ref="menuStart"
                                    v-model="menuStart"
                                    :close-on-content-click="false"
                                    :return-value.sync="startDate"
                                    transition="scale-transition"
                                    offset-y
                                    min-width="auto"
                                >
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-text-field
                                            v-model="startDate"
                                            label="전시 시작일"
                                            prepend-inner-icon="mdi-calendar"
                                            readonly
                                            v-bind="attrs"
                                            v-on="on"
                                            outlined
                                        ></v-text-field>
                                    </template>
                                    <v-date-picker
                                    v-model="startDate"
                                    no-title
                                    scrollable
                                    :weekday-format="getDay"
                                    :month-format="getMonth"
                                    :title-date-format="getMonth"
                                    :header-date-format="getHeaderTitleMonth"
                                    >
                                    <v-spacer></v-spacer>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="menuStart = false"
                                    >
                                        Cancel
                                    </v-btn>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="$refs.menuStart.save(startDate)"
                                    >
                                        OK
                                    </v-btn>
                                    </v-date-picker>
                                </v-menu>
                            </v-col>
                            <v-col md="4">
                                <v-menu
                                    ref="menuEnd"
                                    v-model="menuEnd"
                                    :close-on-content-click="false"
                                    :return-value.sync="endDate"
                                    transition="scale-transition"
                                    offset-y
                                    min-width="auto"
                                >
                                    <template v-slot:activator="{ on, attrs }">
                                        <v-text-field
                                            v-model="endDate"
                                            label="전시 종료일"
                                            prepend-inner-icon="mdi-calendar"
                                            readonly
                                            v-bind="attrs"
                                            v-on="on"
                                            outlined
                                        ></v-text-field>
                                    </template>
                                    <v-date-picker
                                    v-model="endDate"
                                    no-title
                                    scrollable
                                    :weekday-format="getDay"
                                    :month-format="getMonth"
                                    :title-date-format="getMonth"
                                    :header-date-format="getHeaderTitleMonth"
                                    >
                                    <v-spacer></v-spacer>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="menuEnd = false"
                                    >
                                        Cancel
                                    </v-btn>
                                    <v-btn
                                        text
                                        color="primary"
                                        @click="$refs.menuEnd.save(endDate)"
                                    >
                                        OK
                                    </v-btn>
                                    </v-date-picker>
                                </v-menu>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="8">
                                <v-btn block outlined rounded>완료</v-btn>
                            </v-col>
                        </v-row>
                </v-expansion-panel-content>
            </v-expansion-panel>
            
            <v-expansion-panel>
                <v-expansion-panel-header>
                    <v-row class="justify-center">
                        <v-col md="8">
                            <div><v-icon>mdi-numeric-2-circle</v-icon> 전시회 사진 등록</div>
                        </v-col>
                    </v-row>
                </v-expansion-panel-header>
                <v-expansion-panel-content>
                    <v-row class="justify-center">
                        <v-col md="8">
                                <v-file-input
                                accept="image/png"
                                placeholder="전시회 포스터 이미지를 첨부해주세요."
                                prepend-inner-icon="mdi-camera"
                                label="전시회 포스터 이미지"
                                outlined
                                @change="previewPosterImage"
                                v-model="posterImage"
                            ></v-file-input>
                        </v-col>
                    </v-row>
                    <v-row class="justify-center">
                        <v-col md="8">
                            <v-img :src="posterUrl" max-height="300px" max-width="300px" contain></v-img>
                        </v-col>
                    </v-row>
                    <v-row class="justify-center">
                        <v-col md="8">
                                <v-file-input
                                accept="image/png"
                                placeholder="전시회 이미지를 첨부해주세요."
                                prepend-inner-icon="mdi-camera"
                                label="전시회 이미지"
                                outlined
                                @change="previewInnerImage"
                                v-model="innerImage"
                                multiple
                            ></v-file-input>
                        </v-col>
                    </v-row>
                    <v-row >
                        <v-col class="d-flex child-flex" cols="4" v-for="(item, i) in innerUrl" v-bind:key="i">
                            <v-img :src="item" max-height="300px" max-width="300px" contain aspect-ratio="1"></v-img>
                        </v-col>
                    </v-row>
                    <v-row class="justify-center">
                        <v-col md="8">
                            <v-btn block outlined>등록</v-btn>
                        </v-col>
                    </v-row>
                </v-expansion-panel-content>
            </v-expansion-panel>
        </v-expansion-panels>
        
    </v-container>
  </v-app>
</template>

<script>
import nav_bar from '@/components/nav_bar.vue'
export default {
  name: 'exhibitRegisterView',
  components: {
    nav_bar,
  },
  data: () => ({
    // DatePicker용 variable.
    startDate:'',
    menuStart:false, // 시작일 토글용.
    endDate:'',
    menuEnd:false, // 마감일 토글용.
    // 이미지 첨부 및 preview용 variable.
    posterUrl: null,
    posterImage: null,
    innerUrl:[],
    innerImage:null,
  }),
  methods:{
    // DatePicker custom용 methods
    getDay: function(date){
        const daysOfWeek = ['일', '월', '화', '수', '목', '금', '토'];
        let i = new Date(date).getDay(date);
        return daysOfWeek[i];
    },
    getMonth(date) {
    const monthName = [
        '1월',
        '2월',
        '3월',
        '4월',
        '5월',
        '6월',
        '7월',
        '8월',
        '9월',
        '10월',
        '11월',
        '12월',
    ];
    let i = new Date(date).getMonth(date);
    return monthName[i];
    },
    getHeaderTitleMonth(isodate){
        const dateSplit = isodate.split('-')
        const date = dateSplit[0] + '.' + parseInt(dateSplit[1])
        return date
    },
    //
    previewPosterImage() {
        if(this.posterImage!=null){
            this.posterUrl = URL.createObjectURL(this.posterImage);
        } else{
            this.posterUrl = null
        }
    },
    previewInnerImage() {
        if(this.innerImage.length != 0){
            this.innerUrl = []
            this.innerImage.forEach(element =>{
                this.innerUrl.push({'src':URL.createObjectURL(element)})
            })
        } else{
            this.innerUrl = []
        }
    }
  }
}
</script>

<style>
</style>