<template>
    <v-card>
        <v-container>
                <v-row>
                    <v-col md="2">
                        <v-avatar size="100px">
                            <v-img :src="getImgUrl(reviewData.fileDirName + reviewData.fileName)"></v-img>
                        </v-avatar>
                    </v-col>
                    <v-col md="2">
                        <v-card-text class="pb-n4">{{reviewData.usrNickname}}</v-card-text>
                        <v-card-text>{{reviewData.revCretDate}}</v-card-text>
                    </v-col>
                    <v-col md="6">
                        <v-card-text>{{reviewData.revContent}}</v-card-text>
                    </v-col>
                </v-row>
        </v-container>
    </v-card>
</template>

<script>
export default {
    name: 'reviewCard',
    components: {

    },
    data: () => ({

    }),
    props : {
        reviewData: Object,
    },
    methods:{
        getImgUrl(img){
            return require(img);
        }
    }
}
</script>

<style>

</style>