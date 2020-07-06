package com.adtex.NeuromusclarMonitor;


import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class FileSaveDlg extends Dialog {
    private static final LinearLayout.LayoutParams FILL = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);

    private Context m_context;
    private DialogListener m_listener;
    private Button		m_btnRegist, m_btnCancel;

    private EditText	m_FileNameEdit;

    public String m_FileName;
    //	public	int		m_nProtectNo;
    MainActivity	m_ma;
    /*!
     * @brief	コンストラクタ
     *
     * @param	activity	Activity
     * @param	listener	リスナー
     */
    public FileSaveDlg(Activity activity, DialogListener listener) {
        super((Context)activity);

        m_context = (Context)activity;
        m_listener = listener;
        m_btnRegist = null;
        m_btnCancel = null;
        m_FileNameEdit = null;
    }

    /*!
     * @brief	生成イベント
     */
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        m_ma = GlobalVariable.m_ma;
        LayoutInflater factory = LayoutInflater.from(m_context);
        final View entryView = factory.inflate(R.layout.file_save_dlg, null);
        // 登録ボタン.
        m_btnRegist = (Button)entryView.findViewById(R.id.FileSaveOK);
        m_btnRegist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v)
            {
                GetItemValu();
                if(CheckParam())
                {
                    m_listener.onRegistSelected();
                    dismiss();
                }
            }
        });

        // キャンセルボタン.
        m_btnCancel = (Button)entryView.findViewById(R.id.FileSaveCancel);
        m_btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                m_listener.onCancel();
                dismiss();
            }
        });
        //
        String	str;

        m_FileNameEdit = (EditText)entryView.findViewById(R.id.FileName);
        str = m_FileName;
        m_FileNameEdit.setText(str);


        addContentView(entryView, FILL);
    }

    public void GetItemValu()
    {
        String	str;
        try
        {
            m_FileName = m_FileNameEdit.getText().toString();
        }
        catch (Exception e)
        {
            int	err;
            err = 0;
        }


//		m_Comment = m_editComment.getText().toString();
    }

    public boolean CheckParam()
    {
        return true;
    }
    /*!
     * @brief	キー処理
     */
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            m_listener.onCancel();
            dismiss();

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    /*!
     * @brief	ダイアログ処理の結果を通知するためのインターフェース
     */
    public static interface DialogListener {
        /*!
         * @brief	登録ボタン押下
         */
        public abstract void onRegistSelected();

        /*!
         * @brief	キャンセル
         */
        public abstract void onCancel();
    }
}
