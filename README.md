
***************************************************************************************
- Tạo mô hình Socket Server-MultiClient
- Hoàn thiện xong mô hình Database
- Đăng nhập hệ thống
- Đặt lệnh mua, lệnh bán cho từng cổ phiếu, mỗi cổ phiếu sẽ có 1 hàng đợi mua và hàng đợi bán.
- Xem thông tin cổ phiếu mua, bán
- Khớp lệnh với tiêu chí bán rẻ thì được ưu tiên bán trước, mua đắt thì được ưu tiên mua trước sử dụng PriorityBlockingQueue.
- Giá mua phải lớn hơn hoặc bằng giá bán. Nếu giá bằng nhau thì so sánh về số lượng mua, mua nhiều sẽ được mua trước.

***************************************************************************************
