# WebUIS

[1] LOGIN ---------------------> Server
(username + password)

[2] ✅ Server xác thực
🔐 Tạo Access token (ngắn hạn)
🔄 Tạo Refresh token (dài hạn)
💾 Lưu refresh token vào DB
🍪 Gửi refresh token trong cookie

[3] Access token hết hạn
Client gọi GET /refresh-token

[4] ✅ Server kiểm tra cookie
🔐 Decode token
🆚 So sánh DB

[5] Nếu hợp lệ:
🔁 Tạo access token mới
🔄 Tạo refresh token mới
💾 Cập nhật DB
🍪 Gửi cookie mới
