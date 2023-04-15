var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop11_faulty { 
	always all f:File | f in File-Protected implies after f'=Protected
  

}

run  prop11_faulty