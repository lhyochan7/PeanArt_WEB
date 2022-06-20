<template>
    <v-app>
        <nav_bar></nav_bar>
        <v-container fill-height>
            <v-card height="110%" class="rounded-lg">
                <v-container class="px-4 py-4">
                    <v-row>
                        <v-col md="6">
                            <v-img src="../assets/cover.png" height="200%" max-height="556"></v-img>
                        </v-col>
                        <v-col md="6">
                            <v-text-field
                            v-model="email"
                            :rules="[rules.required, rules.email]"
                            label="이메일"
                            outlined
                            ></v-text-field>
                            <v-text-field
                            v-model="password"
                            :append-icon="show_pwd ? 'mdi-eye' : 'mdi-eye-off'"
                            :type="show_pwd ? 'text' : 'password'"
                            :rules="[rules.required]"
                            @click:append="show_pwd = !show_pwd"
                            label="비밀번호"
                            outlined></v-text-field>
                            <v-btn outlined rounded block class="mb-6" @click="loginRequest">들어가기</v-btn>
                            <p class="caption">계정이 아직 없으신가요? <router-link to="/register" style="text-decoration:none; color:black;" class="font-weight-bold">회원가입 하러 가기!</router-link></p>
                        </v-col>
                    </v-row>
                </v-container>
            </v-card>
        </v-container>
        <foot_bar></foot_bar>
    </v-app>
</template>

<script>
import nav_bar from '../components/nav_bar.vue'
import axios from 'axios'
import foot_bar from '@/components/foot_bar.vue';
export default {
    name: 'loginPage',
    components: { nav_bar, foot_bar },
    data: () => ({
        show_pwd:false,
        email:'',
        password:'',
        rules: {
            required: value => !!value || 'Required.',
            email: value => {
            const pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
            return pattern.test(value) || 'Invalid e-mail.'
          },
        }
  }),
   methods: {
      loginRequest: function() {
          axios.post('/loginCheck', {
              usrId: this.email,
              usrPw: this.password
          }, { headers: {
            "Content-Type": `application/json; charset = utf-8`,
          },}).then(response=>{
              console.log(response);
              if(response.status === 200){
                  // 응답이 OK(200) 이면 main 페이지로 이동
                  alert('로그인에 성공했습니다!')
                  sessionStorage.setItem('usrId', this.email);
                  this.$router.push('/main');
              } else {
                  alert('로그인에 실패했습니다. 다시 시도해주세요');
              }
          })
      },
  }
};
</script>

<style>

</style>
