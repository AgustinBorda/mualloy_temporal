var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop11_faulty { 
  after all f:File | f not in Protected implies f in Protected

}

run  prop11_faulty