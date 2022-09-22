<template>
  <v-app id="app">
    <v-container>
      <p></p>
      <div id="notice"><strong>For insert, Click each column!</strong></div>
      <div id="notice">
        <strong>↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓ '▽' ↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓</strong>
      </div>
      <p></p>
      <v-row class="input_row" style="width: 100%">
        <v-text-field
          v-model="input.name"
          label="name"
          ref="input_name"
        ></v-text-field>
        <v-text-field v-model="input.kind" label="kind"></v-text-field>
        <v-text-field v-model="input.price" label="price"></v-text-field>
      </v-row>
      <v-row class="input_row" style="width: 100%">
        <v-text-field v-model="input.date" label="date"></v-text-field>
        <v-text-field v-model="input.seller" label="seller"></v-text-field>
        <v-text-field v-model="input.buyer" label="buyer"></v-text-field>
      </v-row>

      <p></p>

      <v-btn type="button" @click="add_data" >Add Button</v-btn>
      <v-btn v-model="get_data" type="button" @click="all_data">데이터 불러오기</v-btn>
      <p></p>

      <v-data-table style="width: 100%" :headers="headers" :items="contents">
        <template #item="{ item }">
          <tr>
            <td>
              <v-text-field
                v-if="item.modify_row == true"
                v-model="item.modify_name"
                v-bind:placeholder="item.name"
                >{{ item.name }}</v-text-field
              >
              <span v-if="item.modify_row != true">
                {{ item.name }}
              </span>
            </td>
            <td>
              <v-text-field
                v-if="item.modify_row == true"
                v-model="item.modify_kind"
                v-bind:input="item.kind"
                >{{ item.kind }}</v-text-field
              >
              <span v-if="item.modify_row != true">{{ item.kind }}</span>
            </td>
            <td>
              <v-text-field
                v-if="item.modify_row == true"
                v-model="item.modify_price"
                v-bind:placeholder="item.price"
                >{{ item.price }}</v-text-field
              >
              <span v-if="item.modify_row != true">{{ item.price }}</span>
            </td>
            <td>
              <v-text-field
                v-if="item.modify_row == true"
                v-model="item.modify_date"
                v-bind:placeholder="item.date"
                >{{ item.date }}</v-text-field
              >
              <span v-if="item.modify_row != true">{{ item.date }}</span>
            </td>
            <td>
              <v-text-field
                v-if="item.modify_row == true"
                v-model="item.modify_seller"
                v-bind:placeholder="item.seller"
                >{{ item.seller }}</v-text-field
              >
              <span v-if="item.modify_row != true">{{ item.seller }}</span>
            </td>
            <td>
              <v-text-field
                v-if="item.modify_row == true"
                v-model="item.modify_buyer"
                v-bind:placeholder="item.buyer"
                >{{ item.buyer }}</v-text-field
              >
              <span v-if="item.modify_row != true">{{ item.buyer }}</span>
            </td>

            <v-btn type="button" v-if="item.modify_row != true" @click="modify(item)">
              수정
            </v-btn>
            
            <v-btn type="button" v-if="item.modify_row != true" @click="delete_row(item)"  >
              삭제
            </v-btn>
            
            <v-btn type="button" v-if="item.modify_row == true" @click="modify_end(item)">
              수정완료
            </v-btn>
            
          </tr>
        </template>
      </v-data-table>
    </v-container>
  </v-app>
</template>

<script>
export default {
  name: "App",
  data: () => ({
    input: {
      name: "",
      kind: "",
      price: "",
      date: "",
      seller: "",
      buyer: "",
      id: 0,
      modify_row: false,
    },
    row: {
      name: "",
      kind: "",
      price: "",
      date: "",
      seller: "",
      buyer: "",
      id: 0,
      modify_row: false,
    },

    get_data : false,

    headers: [
      { text: "name", value: "name" },
      { text: "kind", value: "kind" },
      { text: "price", value: "price" },
      { text: "date", value: "date" },
      { text: "seller", value: "seller" },
      { text: "buyer", value: "buyer" },
    ], //end of table header

    //위 헤더의 value 값으로 연결
    contents: [
      {
        name: "HW-031",
        kind: "hanwoo",
        price: "13808",
        date: "1999-01-14",
        seller: "홍길동",
        buyer: "강호동",
        id : -1,
        modify_row: false,
      },
     
    ], //end of table components
  }), //end of data
  methods: {

    all_data(){

     


      this.get_data = true;
      this.contents.splice(this.contents, this.contents.length);

      let cardList = new Array();
      
      //api 연동
      this.$axios.get("/api").then(response => {

        //log 찍기
        console.log('axios 성공: ')
        console.log(response.data);
        console.log(response.status);
        console.log(response.statusText);
        console.log(response.headers);
        console.log(response.config);

        cardList = response.data;


        //반복문으로 넣어주기
        for(let i=0;i<cardList.length;i++){

      

        console.log("im in for - :"+ cardList[i].name)
        
        //값 8개
        this.contents.push( { name : cardList[i].name, kind : cardList[i].kind, price : cardList[i].price, date : cardList[i].date,
        seller : cardList[i].seller, buyer : cardList[i].buyer, id:  cardList[i].id, modify_row: false} )

        }


      }).catch(err => {
        console.log('axios 에러'+err)
      })



      

      


    },

    add_data() {
      console.log("clicked!");
      if (
        this.input.name == undefined ||
        this.input.name == "" ||
        this.input.kind == undefined ||
        this.input.kind == "" ||
        this.input.price == undefined ||
        this.input.price == "" ||
        this.input.date == undefined ||
        this.input.date == "" ||
        this.input.seller == undefined ||
        this.input.seller == "" ||
        this.input.buyer == undefined ||
        this.input.buyer == ""
      ) {
        alert("모든 항목을 입력해 주세요.");
        return;
      }

      //this.row = this.input;
      this.row.name = this.input.name;
      this.row.kind = this.input.kind;
      this.row.price = this.input.price;
      this.row.date = this.input.date;
      this.row.seller = this.input.seller;
      this.row.buyer = this.input.buyer;
      this.row.id = -1;
      this.row.modify_row = this.input.modify_row;

      

      if(this.get_data==false){
        //데이터 테이블에 추가
        this.contents.push(this.row);
        console.log(this.row);
      }
      else{

        //api 연동
        this.$axios.post("/api/dataform",
        {
          //get 경우
          //params : {
            "name" : this.input.name,
            "kind" : this.input.kind,
            "price" : this.input.price,
            "date" : this.input.date,
            "seller" : this.input.seller,
            "buyer" : this.input.buyer
        // }
          
          

        }).then(res => {
          console.log('axios 성공' + res.data)
          this.all_data();
        }).catch(err => {
          console.log('axios 에러'+err)
        })
      }
     
      
      //text field 비우기
      // this.$refs.input_name.lazyValue = "";
      this.input.name = "";
      this.input.kind = "";
      this.input.price = "";
      this.input.date = "";
      this.input.seller = "";
      this.input.buyer = "";
      this.input.modify_row = false;

      

    },

    delete_row(item) {

      if(item.id == -1 ){
        //화면만 구현시 
        this.contents.splice(this.contents.indexOf(item), 1);
        
      }

      else {

      
        //삭제 기능
        console.log("삭제 대상 아이디: "+item.id);

        //api 연동
        this.$axios.post("/api/datadelete",
        { "id" : item.id }  
        ).then(res => {
          console.log('axios 성공: '+res)
          this.all_data();
        }).catch(err => {
          console.log('axios 에러'+err)
        })

        

      }

    },
   
    modify(item) {
      item.modify_row = true;
      console.log(item);

      //수정 버튼 눌렀을 시 변화값
      item.modify_name = item.name;
      item.modify_kind = item.kind;
      item.modify_price = item.price;
      item.modify_date = item.date;
      item.modify_seller = item.seller;
      item.modify_buyer = item.buyer;
    },
    
    modify_end(item) {
      console.log(item.modify_row);
      

      //수정완료 버튼 눌렀을 시 변화값 : 수정한 값으로 반영
      if (item.modify_name != undefined) item.name = item.modify_name;
      if (item.modify_kind != undefined) item.kind = item.modify_kind;
      if (item.modify_date != undefined) item.date = item.modify_date;
      if (item.modify_price != undefined) item.price = item.modify_price;
      if (item.modify_seller != undefined) item.seller = item.modify_seller;
      if (item.modify_buyer != undefined) item.buyer = item.modify_buyer;


      if(item.id != -1){

      
        //api 구현
        //수정 기능
        console.log("수정 대상 아이디: "+item.id);
        

        //api 연동
        this.$axios.post("/api/datamodify",
        { 
            "name" : item.name,
            "kind" : item.kind,
            "price" : item.price,
            "date" : item.date,
            "seller" : item.seller,
            "buyer" : item.buyer, 
            "id" : item.id
        }  
        ).then(res => {
          console.log('axios 성공: '+res)
          this.all_data();
        }).catch(err => {
          console.log('axios 에러'+err)
        })

        this.all_data();

      }
      
      item.modify_row = false;
      
      
      
    },
   
  }, //end of methods
};
</script>
<style scoped>
#notice {
  font-size: 30px;
  color: pink;
}
</style>
