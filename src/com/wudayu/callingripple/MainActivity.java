package com.wudayu.callingripple;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText edtPhone;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edtPhone = (EditText) findViewById(R.id.edt_phonenumber);
		edtPhone.addTextChangedListener(new EdtPhoneTextWatcher());
		edtPhone.setOnTouchListener(null);
	}

	class EdtPhoneTextWatcher implements TextWatcher {
		boolean fromUser = true;

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,
				int after) {}
		@Override
		public void onTextChanged(CharSequence s, int start, int before,
				int count) {}
		@Override
		public void afterTextChanged(Editable s) {
			// 普通末尾输入与删除情况
			if (fromUser) {
				fromUser = false;
				String afterNumber = formatNumber(edtPhone.getText().toString());
				edtPhone.setText(afterNumber);
				// 设置光标位置
				edtPhone.setSelection(afterNumber.length());
				return;
			}

			fromUser = true;
		}

		String formatNumber(String number) {
			StringBuffer sb = new StringBuffer(number);
			for (int i = 0; i < sb.length(); ++i)
				if (sb.charAt(i) == ' ')
					sb.deleteCharAt(i);

			if (sb.length() > 7)
				sb.insert(7, ' ');

			if (sb.length() > 3)
				sb.insert(3, ' ');

			return sb.toString().trim();
		}

	}
	
}
