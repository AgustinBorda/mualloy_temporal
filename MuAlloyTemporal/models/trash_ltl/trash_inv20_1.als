var sig File {
	var link : lone File
}
var sig Trash in File {}
var sig Protected in File {}

pred prop20_faulty { 
	always all p: Protected | always p in Trash

}

run  prop20_faulty