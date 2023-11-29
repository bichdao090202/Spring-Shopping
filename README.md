# Các chức năng

**1.Mua hàng**
- Vào http://localhost:8080/home
  ![ERD](/img/Home.png)
- Nhấn "Buy Now" để thêm sản phẩm vào giỏ hàng
- Nhấn "Cart" để vào trang giỏ hàng, có các sản phẩm đã mua và tổng tiền
  ![ERD](/img/cart.png)
- Nhấn "Pay" để vào trang chọn customer. Nhập email và nhấn "Search" để tìm customer theo email
  ![ERD](/img/chooseCustomer.png)
Nếu tìm thấy email thì hiện trang thông tin của customer
  ![ERD](/img/foundCustomer.png)
Nếu tìm không thấy thì hiện trang không tìm thấy
  ![ERD](/img/emailNotExist.png)
- Hoặc nhấn vào link "No. I'm new" để Sign up customer, hiện form:
  ![ERD](/img/formCustomer.png)
Nếu email chưa tồn tại thì Sign up thành công. Customer được thêm vào db
  ![ERD](/img/formCustomer3.png)
  ![ERD](/img/signUpSuccess.png)
Nếu email tồn tại thì hiển thị thông tin customer
  ![ERD](/img/formCustomer2.png)
  ![ERD](/img/emailExist.png)
- Nhấn "Payment" để thanh toán. Đơn hàng được thêm vào db. Xóa giỏ hàng, hiện trang thanh toán thành công
  ![ERD](/img/PaymentSuccess.png)


