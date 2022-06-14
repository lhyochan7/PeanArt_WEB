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
        <v-expansion-panels flat v-model="panelVar" readonly>
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
                    <v-form ref="firstForm">
                        <v-row class="justify-center" no-gutters>
                            <v-col md="2">
                                <v-select
                                v-model="exhibKind"
                                label="*전시회 종류"
                                :items="kindItem"
                                item-text="name"
                                item-value="value"
                                :rules="[rules.required]"
                                outlined></v-select>
                            </v-col>
                            <v-col md="6">
                                <v-text-field
                                v-model="exhibTitle"
                                label="*전시 이름"
                                :rules="[rules.required, rules.title]"
                                outlined></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="6">
                                <v-text-field
                                v-model="exhibLocation"
                                label="*전시회 장소" 
                                :rules="[rules.required]" 
                                outlined></v-text-field>
                            </v-col>
                            <v-col md="2">
                                <v-btn x-large outlined @click="loadAddress()">주소 검색</v-btn>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="8">
                                <v-text-field
                                v-model="exhibUri"
                                :rules="[isURL]"
                                label="전시회 홈페이지 주소"
                                outlined></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="8">
                                <v-textarea
                                v-model="exhibSimpleInfo"
                                label="*간략 설명"
                                :rules="[rules.required, rules.simpleInfo]"
                                outlined auto-grow counter></v-textarea>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="8">
                                <v-textarea
                                v-model="exhibDetailInfo"
                                label="*상세 설명"
                                :rules="[rules.required, rules.detailInfo]"
                                outlined auto-grow counter></v-textarea>
                            </v-col>
                        </v-row>
                        <v-row class="justify-center">
                            <!--전시 시작일 선택용 menu-->
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
                                            label="*전시 시작일"
                                            prepend-inner-icon="mdi-calendar"
                                            readonly
                                            :rules="[rules.required]"
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
                            <!--전시 시작일 선택용 menu 끝-->
                            <!--전시 종료일 선택용 menu-->
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
                                            label="*전시 종료일"
                                            prepend-inner-icon="mdi-calendar"
                                            readonly
                                            v-bind="attrs"
                                            v-on="on"
                                            outlined
                                            :rules="[rules.required]"
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
                            <!--전시 종료일 선택용 menu-->
                        </v-row>
                        <v-row class="justify-center">
                            <v-col md="2">
                                <v-checkbox
                                v-model="exhibGoodsAllow"
                                label="굿즈허용여부"
                                ></v-checkbox>
                            </v-col>
                            <v-col md="6">
                                <v-btn block outlined rounded @click="panelVar=1">완료</v-btn>
                            </v-col>
                        </v-row>
                    </v-form>
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
                                prepend-inner-icon="mdi-image"
                                label="전시회 포스터 이미지"
                                outlined
                                @change="previewPosterImage"
                                v-model="posterImage"
                            ></v-file-input>
                        </v-col>
                    </v-row>
                    <v-row class="justify-center">
                        <v-col md="8" class="d-flex justify-center align-center">
                            <v-img v-if="posterUrl!=null" :src="posterUrl" max-height="300px" max-width="300px" contain></v-img>
                        </v-col>
                    </v-row>
                    <v-row class="justify-center">
                        <v-col md="8">
                                <v-file-input
                                accept="image/png"
                                placeholder="전시회 이미지를 첨부해주세요."
                                prepend-inner-icon="mdi-image-multiple"
                                label="전시회 이미지"
                                outlined
                                @change="previewInnerImage"
                                v-model="innerImage2"
                                multiple
                                :clearable="false"
                            >
                                <template v-slot:selection="{ index, text }">
                                    <v-chip
                                        small
                                        label
                                        color="primary"
                                        close
                                        @click:close="deleteFile(index, text)"
                                    >
                                        {{ text }}
                                    </v-chip>
                                </template>
                            </v-file-input>
                        </v-col>
                    </v-row>
                    <v-row cense>
                        <v-col class="d-flex child-flex justify-center align-center" cols="4" v-for="(item, i) in innerUrl" v-bind:key="i">
                            <v-card>
                                <v-img :src="item" max-height="250px" max-width="250px" contain aspect-ratio="1"></v-img>
                            </v-card>
                        </v-col>
                    </v-row>
                    <v-row class="justify-center">
                        <v-col md="4">
                            <v-btn block outlined @click="panelVar=0">뒤로 가기</v-btn>
                        </v-col>
                        <v-col md="4">
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
    innerImage:[],
    innerImage2:[],
    // 종류 select 용 Variable
    kindItem: [
        {name:'대학 전시회', value:1}, 
        {name:'개인 전시회', value:2}, 
        {name:'기타', value:3}
    ],
    panelVar: 0,
    // 전시회 등록용 Variable
    exhibTitle:'',
    exhibLocation:'',
    exhibUri:'',
    exhibSimpleInfo:'',
    exhibDetailInfo:'',
    exhibKind:'',
    exhibGoodsAllow:'',
    // Input 검증 용 Rules
    rules: {
        required: value => !!value || '필수.',
        title: value => value.length<1000 || '제목은 1000자 이내여야 합니다.',
        simpleInfo: value => value.length < 100 || '간략 설명은 100자 이내여야 합니다.',
        detailInfo: value => value.length < 10000 || '상세 설명은 10000자 이내여야 합니다.',
    },
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
    // 첨부사진 preview용 method
    previewPosterImage() {
        if(this.posterImage!=null){
            this.posterUrl = URL.createObjectURL(this.posterImage);
        } else{
            this.posterUrl = null
        }
    },
    previewInnerImage() {
        for (var file of this.innerImage2){
            if(this.innerImage.find(image => image.name == file.name) == undefined){
                this.innerImage.push(file)
                this.innerUrl.push({'src':URL.createObjectURL(file)})
            }
        }
        this.innerUrl = []
        console.log(this.innerImage)
        this.innerImage.forEach(element =>{
            this.innerUrl.push({'src':URL.createObjectURL(element)})
        })
        this.innerImage2 == [];
    },
    deleteFile(index, text){
        console.log(text)
        this.innerImage.splice(index, 1)
        this.innerUrl.splice(index, 1)
        this.previewInnerImage()
    },
    // URL 판별용 Method
    isURL(value) {
      let url;
      if(value.length === 0){
        return true;
      }
      try {
        url = new URL(value);
      } catch (_) {
        return '올바르지 않은 URL 입니다.';
      }
      return (url.protocol === "http:" || url.protocol === "https:") || '올바르지 않은 URL 입니다.';
    },
    loadAddress: function() {
          // 주소 입력위해 Daum 주소검색 API 호출 
          new window.daum.Postcode({
              onComplete: (data) => {
                  this.exhibLocation = data.roadAddress;
              }
          }).open();
      },
      nextStep: function() {
        if(this.$refs.firstForm.validate()){
           this.panelVar=1
        }
      }
  },
    mounted() {
        let Script = document.createElement("script");
        Script.setAttribute("src", "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js");
        document.head.appendChild(Script);
    }
}
</script>

<style>
</style>