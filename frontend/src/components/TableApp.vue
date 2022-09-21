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

      <v-btn type="button" @click="add_data">Add Button</v-btn>
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

            <v-btn v-if="item.modify_row != true" @click="modify(item)">
              수정
            </v-btn>
            <v-btn @click="delete_row(item)" v-if="item.modify_row != true" >
              삭제
            </v-btn>
            <v-btn v-if="item.modify_row == true" @click="modify_end(item)">
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
      modify_row: false,
    },
    row: {
      name: "",
      kind: "",
      price: "",
      date: "",
      seller: "",
      buyer: "",
      modify_row: false,
    },

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
        date: "2020-10-21",
        seller: "김첨지",
        buyer: "닐 잭슨",
        modify_row: false,
      },
      {
        name: "HW-031",
        kind: "hanwoo",
        price: "13808",
        date: "2020-10-21",
        seller: "김첨지",
        buyer: "닐 잭슨",
        modify_row: false,
      },
      {
        name: "iphone",
        kind: "device",
        price: "10000000",
        date: "2022-09-15",
        seller: "clara",
        buyer: "iron",
        modify_row: false,
      },
    ], //end of table components
  }), //end of data
  methods: {
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
      this.row.modify_row = this.input.modify_row;

      //데이터 테이블에 추가
      this.contents.push(this.row);
      console.log(this.row);

  
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
        console.log('axios 성공: '+res)
      }).catch(err => {
        console.log('axios 에러'+err)
      })

     
      
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
      this.contents.splice(this.contents.indexOf(item), 1);
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
      item.modify_row = false;
      //수정완료 버튼 눌렀을 시 변화값 : 수정한 값으로 반영
      if (item.modify_name != undefined) item.name = item.modify_name;
      if (item.modify_kind != undefined) item.kind = item.modify_kind;
      if (item.modify_date != undefined) item.date = item.modify_date;
      if (item.modify_price != undefined) item.price = item.modify_price;
      if (item.modify_seller != undefined) item.seller = item.modify_seller;
      if (item.modify_buyer != undefined) item.buyer = item.modify_buyer;
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
