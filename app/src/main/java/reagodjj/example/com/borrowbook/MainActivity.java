package reagodjj.example.com.borrowbook;

import android.support.v7.app.AppCompatActivity;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener,
        View.OnClickListener, CompoundButton.OnCheckedChangeListener, SeekBar.OnSeekBarChangeListener {
    private EditText etReader, etBorrowTime;
    private RadioGroup rgSex;
    private CheckBox cbHistory, cbSuspense, cbLiterary;
    private TextView tvAgeShow, tvBookName, tvBookType, tvBookForAge, tvBookNum;
    private SeekBar sbChangeAge;
    private RadioButton rbMale, rbFemale;
    private ImageView ivBook;
    private Button btSearch, btNext;
    private boolean isHistory, isSuspense, isLiterary;
    private Person person;
    private List<Book> bookList;
    private List<Book> bookGetList;
    private String deadline = "2016-06-30";
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();
        initData();
    }

    private void initView() {
        etReader = findViewById(R.id.et_reader);
        rgSex = findViewById(R.id.rg_sex);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        etBorrowTime = findViewById(R.id.et_borrow_time);
        cbHistory = findViewById(R.id.cb_history);
        cbSuspense = findViewById(R.id.cb_suspense);
        cbLiterary = findViewById(R.id.cb_literary);
        tvAgeShow = findViewById(R.id.tv_age_show);
        sbChangeAge = findViewById(R.id.sb_change_age);
        ivBook = findViewById(R.id.iv_book);
        tvBookName = findViewById(R.id.tv_book_name);
        tvBookType = findViewById(R.id.tv_book_type);
        tvBookForAge = findViewById(R.id.tv_book_for_age);
        tvBookNum = findViewById(R.id.tv_book_num);
        btSearch = findViewById(R.id.bt_search);
        btNext = findViewById(R.id.bt_next);
    }

    private void initListener() {
        rgSex.setOnCheckedChangeListener(this);
        cbHistory.setOnCheckedChangeListener(this);
        cbSuspense.setOnCheckedChangeListener(this);
        cbLiterary.setOnCheckedChangeListener(this);
        sbChangeAge.setOnSeekBarChangeListener(this);
        btSearch.setOnClickListener(this);
        btNext.setOnClickListener(this);
    }

    private void initData() {
        person = new Person();
        bookList = new ArrayList<>();
        bookGetList = new ArrayList<>();
        //仅仅是为了练习，不连接这几本书是不是属于这个类型
        bookList.add(new Book("人生感悟", 20, false, false, false, R.drawable.aa));
        bookList.add(new Book("边城", 25, false, false, false, R.drawable.bb));
        bookList.add(new Book("sapir", 20, true, false, false, R.drawable.cc));
        bookList.add(new Book("光辉岁月", 35, false, false, true, R.drawable.dd));
        bookList.add(new Book("宋词300首", 6, true, false, true, R.drawable.ee));
        bookList.add(new Book("中国古代文学", 15, true, false, true, R.drawable.ff));
        bookList.add(new Book("无花果", 15, false, true, true, R.drawable.gg));
        bookList.add(new Book("古镇记忆", 30, true, false, false, R.drawable.hh));
    }

    @Override
    public void onClick(View v) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        switch (v.getId()) {
            case R.id.bt_search:
                bookGetList.clear();
                if (etReader.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_name, Toast.LENGTH_SHORT).show();
                else if (etBorrowTime.getText().toString().equals(""))
                    Toast.makeText(MainActivity.this, R.string.no_borrow_time, Toast.LENGTH_SHORT).show();
                else if (!rbMale.isChecked() && !rbFemale.isChecked())
                    Toast.makeText(MainActivity.this, R.string.no_choose_sex, Toast.LENGTH_SHORT).show();
                else {

                    String borrowTime = etBorrowTime.getText().toString();
                    try {
                        Date dateDeadline = simpleDateFormat.parse(deadline);
                        Date dateBorrowTime = simpleDateFormat.parse(borrowTime);

                        if (dateBorrowTime.getTime() >= dateDeadline.getTime()) {
                            Toast.makeText(MainActivity.this, R.string.time_error,
                                    Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            person.setName(etReader.getText().toString());
                            person.setBorrowTime(borrowTime);
                            checkBook();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case R.id.bt_next:
                if (count != bookGetList.size() - 1) {
                    count++;
                    showInformation(count);
                } else {
                    Toast.makeText(MainActivity.this, R.string.show_end, Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_male:
                person.setSex("男");
                break;
            case R.id.rb_female:
                person.setSex("女");
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CheckBox cbBox = (CheckBox) buttonView;
        switch (cbBox.getId()) {
            case R.id.cb_history:
                isHistory = isChecked;
                break;

            case R.id.cb_suspense:
                isSuspense = isChecked;
                break;

            case R.id.cb_literary:
                isLiterary = isChecked;
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        //年龄变动影响适合年龄的读物
        bookGetList.clear();
        ivBook.setImageResource(R.drawable.f);
        tvAgeShow.setText(String.valueOf(seekBar.getProgress()));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        tvAgeShow.setText(String.valueOf(seekBar.getProgress()));
        person.setAge(seekBar.getProgress());
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        person.setAge(seekBar.getProgress());
    }

    private void checkBook() {
        //找出对应的读物
        count = 0;
        for (int i = 0; i < bookList.size(); i++) {
            Book book = bookList.get(i);
            if (person.getAge() >= book.getBookForAge() - 5 &&
                    person.getAge() <= book.getBookForAge() + 5 && book.isHistory() == isHistory &&
                    book.isSuspense() == isSuspense && book.isLiterary() == isLiterary) {
                bookGetList.add(book);
            }
        }
        if (bookGetList.size() == 0) {
            Toast.makeText(MainActivity.this, R.string.find_no_book, Toast.LENGTH_SHORT).show();
        } else {
            showInformation(count);
            btNext.setEnabled(true);
        }
    }

    private void showInformation(int count) {
        ivBook.setImageResource(bookGetList.get(count).getPicture());
        tvBookName.setText(bookGetList.get(count).getBookName());
        tvBookForAge.setText(String.valueOf(bookGetList.get(count).getBookForAge()));
        String bookType = (bookGetList.get(count).isHistory() ? "历史 " : "") +
                (bookGetList.get(count).isSuspense() ? "悬疑 " : "") +
                (bookGetList.get(count).isLiterary() ? "文艺 " : "");
        if (bookType.equals("")) {
            bookType = "暂无分类！";
        }
        tvBookType.setText(bookType);
        Toast.makeText(MainActivity.this, person.toString(), Toast.LENGTH_SHORT).show();
        tvBookNum.setText(String.format(getResources().getString(R.string.book_number),
                String.valueOf(bookGetList.size() - 1 - count)));
    }
}
