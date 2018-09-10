package main

import (
	"fmt"
	"path"
	"net/http"
	"bytes"
	"crypto/hmac"
	"crypto/sha512"
	"encoding/base64"
)

func main() {
    bodyReader := bytes.NewReader(nil)
    bodyString := []byte(nil)
    req, _ := http.NewRequest("POST", "https://wallet.sandbox.swipetech.io/accounts", bodyReader)
    stringToSign2 := []byte(fmt.Sprintf("%s%s", path.Clean(req.URL.Path), bodyString))
    hash := hmac.New(sha512.New384, []byte("12bba2c89baa3a770db410dfacdb70893e5b36a001bd4885c827bc48e96dbbab"))
    fmt.Println(base64.StdEncoding.EncodeToString(hash.Sum(nil)))
    hash.Write(stringToSign2)
    fmt.Println(base64.StdEncoding.EncodeToString(hash.Sum(nil)))
}