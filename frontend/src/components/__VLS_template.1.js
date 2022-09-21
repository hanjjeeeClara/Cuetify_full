export default (await import('vue')).defineComponent({
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
],


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
}),
methods: {
add_data() {
console.log("clicked!");
if (this.input.name == undefined ||
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
this.input.buyer == "") {
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

//text field 비우기
// this.$refs.input_name.lazyValue = "";
this.input.name = "";
this.input.kind = "";
this.input.price = "";
this.input.date = "";
this.input.seller = "";
this.input.buyer = "";
this.input.modify_row = false;



//db연동
let params = new URLSearchParams();
params.append("input_name", this.input.name);
params.append("input_kind", this.input.kind);
params.append("input_price", this.input.price);
params.append("input_date", this.input_date);
params.append("input_seller", this.input.seller);
params.append("input_buyer", this.input.buyer);

this.axios.post("server/add.jsp", params).then((response) => {
if (response.data.result == true) {
//add 완료 알림
alert("데이터 추가가 완료되었습니다.");
this.$router.push("/");
}
});

//add 완료 알림
//alert("데이터 추가가 완료되었습니다.");
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
if (item.modify_name != undefined)
item.name = item.modify_name;
if (item.modify_kind != undefined)
item.kind = item.modify_kind;
if (item.modify_date != undefined)
item.date = item.modify_date;
if (item.modify_price != undefined)
item.price = item.modify_price;
if (item.modify_seller != undefined)
item.seller = item.modify_seller;
if (item.modify_buyer != undefined)
item.buyer = item.modify_buyer;
},
}, //end of methods
});
function __VLS_template() {
// @ts-ignore
[input, input_name, add_data, headers, contents, modify, delete_row, modify_end];
return {};
}
