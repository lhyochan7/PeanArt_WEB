<template>
    <v-app>
        <nav_bar></nav_bar>
        <v-container height="100%">
            <v-row>
                <v-col md="6">
                    <v-img src="../assets/cover.png" height="100%"></v-img>
                </v-col>
                <v-col md="6">
                    <v-form ref="form">
                        <div class="d-flex">
                            <v-text-field
                            v-model="usr_email"
                            :rules="usr_emailChecked ? [] : [rules.required, rules.email, emailValid]"
                            label="이메일"
                            outlined
                            :disabled="usr_emailChecked"
                            ></v-text-field>
                            <v-btn class="ml-2" x-large outlined @click="usr_emailChecked ? clearEmail() : emailCheckRequest()">{{usr_emailChecked? '중복확인 취소': '중복확인'}}</v-btn>
                        </div>
                        <v-alert type="error" :value="emailAlert" transition="slide-y-transition" class="mt-n7">중복된 이메일이 존재합니다. 다시 시도해주세요.</v-alert>
                        <div class="d-flex">
                            <v-text-field
                            v-model="usr_nickname"
                            :rules="usr_nicknameChecked ? [] : [rules.required, nicknameValid]"
                            label="닉네임"
                            outlined
                            :disabled="usr_nicknameChecked"
                            ></v-text-field>
                            <v-btn class="ml-2" x-large outlined @click="usr_nicknameChecked ? clearNickname() : nicknameCheckRequest()">{{usr_nicknameChecked? '중복확인 취소': '중복확인'}}</v-btn>
                        </div>
                        <v-alert type="error" :value="nicknameAlert" transition="slide-y-transition" class="mt-n7">중복된 닉네임이 존재합니다. 다시 시도해주세요.</v-alert>
                        <v-text-field
                        v-model="password"
                        :append-icon="show_pwd ? 'mdi-eye' : 'mdi-eye-off'"
                        :type="show_pwd ? 'text' : 'password'"
                        :rules="[rules.required, rules.password_rule]"
                        @click:append="show_pwd = !show_pwd"
                        label="비밀번호"
                        outlined></v-text-field>
                        <v-text-field
                        v-model="passwordCheck"
                        :append-icon="show_pwd ? 'mdi-eye' : 'mdi-eye-off'"
                        :type="show_pwd ? 'text' : 'password'"
                        :rules="[rules.required, matchPassword, rules.password_rule]"
                        @click:append="show_pwd = !show_pwd"
                        label="비밀번호 확인"
                        outlined></v-text-field>
                        <v-text-field
                        v-model="usr_name"
                        :rules="[rules.required, rules.name]"
                        label="이름"
                        outlined
                        ></v-text-field>
                        <v-text-field
                        v-model="usr_phone"
                        label="휴대폰 번호 (선택사항)"
                        :rules="[rules.phoneNumber]"
                        outlined
                        @input="acceptNumber"
                        ></v-text-field>
                        <v-text-field
                        v-model="usr_addr"
                        label="주소 (선택사항)"
                        outlined
                        @click="loadAddress"
                        readonly
                        ></v-text-field>
                    </v-form>
                    <v-btn outlined rounded block @click="registerRequest">회원가입</v-btn>
                </v-col>
            </v-row>
        </v-container>
    </v-app>
</template>

<script>
import nav_bar from '../components/nav_bar.vue'
import axios from 'axios'
export default {
    name: 'userRegisterPage',
    components: { nav_bar },
    data: () => ({
        show_pwd:false,
        usr_email:'',
        usr_emailChecked:false,
        password:'',
        passwordCheck:'',
        usr_name:'',
        usr_nickname:'',
        usr_nicknameChecked:false,
        usr_addr:'',
        usr_phone:'',
        emailAlert:false,
        nicknameAlert:false,
        rules: {
            required: value => !!value || '필수.',
            email: value => {
                // 이메일 패턴과 같은지 비교 ( 특문아닌 문자or숫자 + @ + (아이피) or ( 영문 . 영문 2자이상))
                const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
                return (value.length < 50 && pattern.test(value)) || '올바르지 않은 e-mail 입니다.'
          },
            password_rule: value => {
                const pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])(?=.{8,})/;
                return (
                    pattern.test(value) ||
                    "비밀번호는 8자 이상, 영문 대, 소문자 및 특수기호를 조합하여야 합니다."
                );
            },
            name: value =>{
                return value.length < 20 || '이름은 20자 이내여야 합니다.'
            },
            nickname: value => value.length < 50 || '닉네임은 50자 이내여야 합니다.',
            phoneNumber: value => {
                const pattern = /^[0-9]{3}-[0-9]{4}-[0-9]{4}$/
                return ( value.length == 0 || pattern.test(value) || "올바른 휴대폰 번호를 입력해 주세요.");
            }
        }
  }),
  methods: {
      // ---------------- 검증(rules)용 functions -----------------
      matchPassword: function() {
          // 비밀번호 == 비밀번호 확인 인지 체크하는 함수
          if (this.password === this.passwordCheck){
              return true;
          } else{
              return '비밀번호가 다릅니다.';
          }
      },
      nicknameValid: function(value) {
          if (value.length < 50){
              if(this.usr_nicknameChecked === true){
                  return true;
              } else{
                  return '닉네임 중복확인이 필요합니다.';
              }
          }
            return '닉네임은 50자 이내여야 합니다.';
      },
      emailValid: function(){
          if(this.usr_emailChecked === true){
              return true;
          }
          return '이메일 중복확인이 필요합니다.';
      },
      // ----------------------------------------------------------
      loadAddress: function() {
          // 주소 입력위해 Daum 주소검색 API 호출 
          new window.daum.Postcode({
              onComplete: (data) => {
                  this.usr_addr = data.roadAddress;
              }
          }).open();
      },
      acceptNumber: function() {
          // 휴대폰 번호 입력시 XXX-XXXX-XXXX 구조로 변환시켜줌
          var x = this.usr_phone.replace(/\D/g, '').match(/(\d{0,3})(\d{0,4})(\d{0,4})/);
          this.usr_phone = !x[2] ? x[1] : x[1] + '-' + x[2] + (x[3] ? '-' + x[3] : '');
          },
    // ---------- 회원가입 요청 함수 -------------
      registerRequest: function() {
          if(this.$refs.form.validate()){
              // 모든 form이 올바르게 입력되었을때.
              const params = {
                  usrId: this.usr_email,
                  usrPw: this.password,
                  usrNickname: this.usr_nickname,
                  usrName: this.usr_name,
                  usrAdrs: this.usr_addr,
                  usrPhone: this.usr_phone
              }
              console.log(params)
              axios.post("http://localhost:8080/join", params,{ headers: {
            "Content-Type": `application/json`,
          },}).then(response => {
              console.log(response);
              if(response.status === 201){
                  // 응답이 OK(201) 이면 login 페이지로 이동
                  alert('회원가입에 성공했습니다!')
                  this.$router.push('/login');
              } else {
                  alert('회원가입에 실패했습니다. 다시 시도해주세요');
              }
          })
          }
      },
      // ---------- 닉네임, 이메일 중복확인 및 중복확인 취소 작업용 함수 ----------
      emailCheckRequest: function() {
          if(this.usr_email.length === 0){
              // 입력한 값이 없을 때 아무 행동 X
              return;
          }
          const param = { params:{email: this.usr_email} }
          axios.get("http://localhost:8080/duple-check", param).then(response =>{
              console.log(response);
              if(response.status === 404){
                  alert('에러가 발생했습니다. 잠시후 시도해주세요.');
                  return;
              }
              if(response.data.type === "email"){
                    if(response.data.duplicated === false){
                        this.usr_emailChecked = true;
                    } else{
                        this.usr_emailChecked = false;
                        this.emailAlert = true;
                        setTimeout(()=>{
                            this.emailAlert = false;
                        },3000)
                    }
                }
          });
      },
      nicknameCheckRequest: function() {
          if(this.usr_nickname.length === 0){
              // 입력한 값이 없을 때 아무 행동 X
              return;
          }
          const param = { params:{nickname: this.usr_nickname} }
          axios.get("http://localhost:8080/duple-check", param).then(response =>{
              console.log(response);
              if(response.status === 404){
                  alert('에러가 발생했습니다. 잠시후 시도해주세요.');
                  return;
              }
              if(response.data.type === "nickname"){
                    if(response.data.duplicated === false){
                        this.usr_nicknameChecked = true;
                    } else{
                        this.usr_nicknameChecked = false;
                        this.emailAlert = true;
                        setTimeout(()=>{
                            this.emailAlert = false;
                        },3000)
                    }
                }
          });
        },
      clearEmail: function() {
          this.usr_email = '';
          this.usr_emailChecked = false;
      },
      clearNickname: function() {
          this.usr_nickname = '';
          this.usr_nicknameChecked = false;
      },
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