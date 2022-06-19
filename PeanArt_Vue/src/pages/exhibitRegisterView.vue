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
                            <v-col md="8">
                                <template>
                                <v-select
                                    v-model="exhibTheme"
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
                        <v-row class="justify-center">
                            <v-col md="6">
                                <v-text-field
                                v-model="exhibLocation"
                                label="*전시회 장소" 
                                :rules="[rules.required, rules.location]" 
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
                                :rules="[isURL, rules.uri]"
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
                                <v-btn block outlined rounded @click="nextStep()">완료</v-btn>
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
                    <v-form ref="secondForm">
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
                                    :rules="[rules.required]"
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
                                    :rules="[innerFileRule]"
                                >
                                </v-file-input>
                                <v-row>
                                    <v-col cols="4" v-for="(item, index) in innerImage" v-bind:key="index">
                                        <v-chip
                                            small
                                            label
                                            color="primary"
                                            close
                                            @click:close="deleteFile(index, item.name)"
                                        >
                                            {{ '...'.concat(item.name.slice(item.name.length-20)) }}
                                        </v-chip>
                                    </v-col>  
                                </v-row>                             
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col class="d-flex child-flex justify-center align-center" cols="4" v-for="(item, i) in innerUrl" v-bind:key="i">
                                <v-card class="justify-center" align="center">
                                    <v-img :src="item" max-height="250px" max-width="250px" contain aspect-ratio="1"></v-img>
                                </v-card>
                            </v-col>
                        </v-row>
                    </v-form>
                    <v-row class="justify-center">
                        <v-col md="4">
                            <v-btn block outlined @click="panelVar=0">뒤로 가기</v-btn>
                        </v-col>
                        <v-col md="4">
                            <div v-if="this.exhibData == null">
                                <v-btn block outlined @click="registerRequest()">등록</v-btn>
                            </div>
                            <div v-else>
                                <v-btn block outlined @click="requestModify()">수정</v-btn>
                            </div>
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
import axios from 'axios';
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
    // theme 선택 selectbox 용 variable
    exhibTheme: [],
    items: ['디자인', '서양화', '사진', '현대미술', '미디어아트', '풍경화', '모던한', '유화', '심플', '세련된', '변화의', '비판적', '멀티컬러', '밝은', '어두운', '추상화'],
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
    exhibKind:0,
    exhibGoodsAllow:'',
    // 전시회 수정용 Variable
    exhibCretDate:'',
    exhibModDate:'',
    fileDirName:'',
    fileName:'',
    usrSeq:'',
    // Input 검증 용 Rules
    rules: {
        required: value => !!value || '필수.',
        title: value => value.length<1000 || '제목은 1000자 이내여야 합니다.',
        simpleInfo: value => value.length < 100 || '간략 설명은 100자 이내여야 합니다.',
        detailInfo: value => value.length < 10000 || '상세 설명은 10000자 이내여야 합니다.',
        location: value => value.length < 1000 || '장소 위치는 1000자 이내여야 합니다.',
        uri: value => value.length < 500 || 'URI는 500자 이내여야 합니다.',
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
    // theme 선택 selectbox용 variable
    remove (item) {
        this.exhibTheme.splice(this.exhibTheme.indexOf(item), 1)
        this.exhibTheme = [...this.exhibTheme]
      },
    // 첨부사진 preview용 method
    previewPosterImage() {
        if(this.posterImage!=null){
            console.log(this.posterImage)
            this.posterUrl = URL.createObjectURL(this.posterImage);
            console.log(this.posterUrl)
        } else{
            this.posterUrl = null
        }
    },
    previewInnerImage() {
        for (var file of this.innerImage2){
            console.log(file)
            if(this.innerImage.find(image => image.name == file.name) == undefined){
                this.innerImage.push(file)
            }
        }
        this.innerUrl = []
        console.log(this.innerImage)
        this.innerImage.forEach(element =>{
            this.innerUrl.push({'src':URL.createObjectURL(element)})
        })
        this.innerImage2 = [];
    },
    deleteFile(index, text){
        console.log(text)
        this.innerImage.splice(index, 1)
        this.innerUrl.splice(index, 1)
        this.previewInnerImage()
    },
    // 이미지 Validate용
    innerFileRule() {
        console.log(this.innerImage.length)
        return ( this.innerImage.length > 0 && this.innerImage.length < 5 ) || '이미지는 1장 이상, 4장 이내로 첨부 가능합니다.'
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
      // 1번째 폼 ( 기본정보 ) 검증 함수
      nextStep: function() {
        if(this.$refs.firstForm.validate()){
           this.panelVar=1
        }
      },
      // 전시회 등록 요청 함수
      registerRequest: function() {
        if(this.$refs.secondForm.validate()){
            const frm = new FormData();
            this.innerImage.forEach(element =>{
                console.log(element)
                frm.append('uploadFile',element)
                })
            frm.append('posterFile', this.posterImage)
            this.exhibGoodsAllow = this.exhibGoodsAllow ? 1 : 0
            const data = {
                    'exhibTitle':this.exhibTitle,
                    'exhibKind':this.exhibKind,
                    'exhibTheme':this.exhibTheme.join(','),
                    'exhibSimpleExp':this.exhibSimpleInfo,
                    'exhibDetailExp':this.exhibDetailInfo,
                    'exhibStartDate':this.startDate,
                    'exhibEndDate':this.endDate,
                    'exhibLocation':this.exhibLocation,
                    'exhibUri':this.exhibUri,
                    'goodsAllow':this.exhibGoodsAllow
            }
            frm.append('exhibData', new Blob([JSON.stringify(data)], {type: "application/json"}))
            axios.post("http://localhost:8080/exhib/register", frm,{ headers: {
                "Content-Type": undefined,
                'Allow-Control-Allow-Origin': '*'
            },}).then(response => {
                console.log(response);
                if(response.status === 200){
                    // 응답이 Ok(200) 이면 전시회 목록 페이지로 이동
                    alert('등록에 성공했습니다!')
                    this.$router.push('/exhib/list?kind=0');
                } else {
                    alert('등록에 실패했습니다. 다시 시도해주세요');
                }
            })   
        } else{
            alert('전시회 이미지는 필수로 첨부 되어야 합니다.');  
        }
      },
      requestModify: function(){
        if(this.$refs.secondForm.validate()){
            const frm = new FormData();
            this.innerImage.forEach(element =>{
                console.log(element)
                frm.append('uploadFile',element)
                })
            frm.append('posterFile', this.posterImage)
            this.exhibGoodsAllow = this.exhibGoodsAllow ? 1 : 0
            const data = {
                    'exhibTitle':this.exhibTitle,
                    'exhibKind':this.exhibKind,
                    'exhibTheme':this.exhibTheme.join(','),
                    'exhibSimpleExp':this.exhibSimpleInfo,
                    'exhibDetailExp':this.exhibDetailInfo,
                    'exhibStartDate':this.startDate,
                    'exhibEndDate':this.endDate,
                    'exhibLocation':this.exhibLocation,
                    'exhibUri':this.exhibUri,
                    'goodsAllow':this.exhibGoodsAllow,
                    'exhibCretDate':this.exhibCretDate,
                    'exhibModDate':this.exhibModDate,
                    'usrSeq':this.usrSeq,
                    'fileDirName':this.fileDirName,
                    'fileName':this.fileName,
            }
            frm.append('exhibData', new Blob([JSON.stringify(data)], {type: "application/json"}))
            axios.post("/detailModifiy", frm,{ headers: {
                "Content-Type": undefined,
                'Allow-Control-Allow-Origin': '*'
            },}).then(response => {
                console.log(response);
                if(response.status === 200){
                    // 응답이 Ok(200) 이면 전시회 목록 페이지로 이동
                    alert('수정에 성공했습니다!')
                    this.$router.push('/exhib/list?kind=0');
                } else {
                    alert('수정에 실패했습니다. 다시 시도해주세요');
                }
            })   
        } else{
            alert('전시회 이미지는 필수로 첨부 되어야 합니다.');  
        }
      },
      // 테마 validation 용 함수
      themeSelect() {
        return (this.exhibTheme.length < 6 && this.exhibTheme.length > 0 ) || '주제는 1개 이상 선택 해야하며, 5개 이하로 선택 가능합니다.';
      },
      // url을 파일로 바꿔주는 함수
      convertURLtoFile: async function(url) {
        console.log(url)
        const response = await fetch('http://localhost:8080/imagePath/'+url);
        const data = await response.blob();
        const ext = url.split(".").pop(); // url 구조에 맞게 수정할 것
        const filename = url.split("/").pop(); // url 구조에 맞게 수정할 것
        const metadata = { type: `image/${ext}` };
        if(filename!=null)
            return new File([data], filename, metadata);
      }
  },
    mounted() {
        let Script = document.createElement("script");
        Script.setAttribute("src", "https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js");
        document.head.appendChild(Script);
        console.log(this.exhibData)
        if(this.exhibData!=null){
            console.log(this.exhibData)
            this.exhibTitle = this.exhibData.exhibTitle;
            this.exhibLocation = this.exhibData.exhibLocation
            this.exhibUri = this.exhibData.exhibUri
            this.exhibSimpleInfo = this.exhibData.exhibSimpleInfo
            this.exhibDetailInfo = this.exhibData.exhibDetailInfo
            this.startDate = this.exhibData.startDate,
            this.endDate = this.exhibData.endDate
            this.exhibKind = this.exhibData.exhibKind
            this.exhibGoodsAllow = this.exhibData.exhibGoodsAllow
            this.usrSeq = this.exhibData.usrSeq
            this.fileDirName = this.exhibData.fileDirName
            this.fileName = this.exhibData.fileName
            this.convertURLtoFile(this.exhibData.fileDirName+'/'+this.exhibData.fileName.replace('PNG', 'png')).then(response=>{
                console.log(response)
                this.posterImage = response
                this.previewPosterImage()
            })
            console.log(this.exhibData.fileList)
            this.exhibData.fileList.forEach(element =>{
                this.convertURLtoFile(element.fileDirName+'/'+element.fileUuid+'_'+element.fileName.replace('PNG', 'png')).then(response=>{
                this.innerImage.push(response)
                this.previewInnerImage()
                })
            })
            console.log(this.exhibData.exhibTheme.split(','))
            this.exhibTheme = this.exhibData.exhibTheme.split(',')
        } else{
            if (String(this.$route.path).match('modify')!=null){
                alert('비정상적인 접근입니다.')
                this.$router.push('/main')
            }
        }
    },
    props : {
        exhibData: Object,
    }
}
</script>

<style>
</style>