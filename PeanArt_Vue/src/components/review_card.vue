<template>
    <v-card>
        <v-row>
            <v-col md="2">
                <v-avatar size="100px">
                    <v-img :src="getImgUrl()"></v-img>
                </v-avatar>
            </v-col>
            <v-col md="2">
                <v-card-text></v-card-text>
            </v-col>
            <v-col md="6">

            </v-col>
            <v-col md="2">
                <v-btn>
                    
                </v-btn>
            </v-col>
        </v-row>
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