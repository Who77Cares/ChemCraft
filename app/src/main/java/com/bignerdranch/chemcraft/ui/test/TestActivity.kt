package com.bignerdranch.chemcraft.ui.test

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bignerdranch.chemcraft.databinding.ActivityTestScreenBinding

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTestScreenBinding.inflate(layoutInflater)

        val itemId = intent.getStringExtra("ITEM_ID")

        setContentView(binding.root)

        binding.testText.text =
            "asddddddjidsofjisodjfiwjeio[fdjweopfjkpo weo ffio kj0i wje jf jwe9j" +
                    "sdiufhuwiehfuijwehfudsjhfjsdhfjsdhjfhsdjfj;sdhjfjs  sdfhjiu sdpiuofj dsi" +
                    "asuihd ashd uiahsd hipuhw7e8 u9 0w9e ir90kwe kc w98eidfkj idsjkc 89wjeocj k8w2ej" +
                    "asdfuhwe jf9889kkkx kl2qw0l- o0v3r- ov i93i 90i9203i90ifw9e0if90i 9wi0e9id0" + "" +
                    "idsujf8jsdifoj wj9ie 9fjiw98ej j we8jf j8we98j-f w98ejf we-98j f9-8wejf jwejf89wew" +
                    "adjj j d98j92j3j980123jd 9kvck 903kfv 893j-98jd 2h3hd 78h23 7d80h23 78hd78  mc238u-" +
                    "dhh hj 0i912i90 iodskcm, im2uiecj -98u2jc-98ujewdui9j1892jsd89jjjjd jsaijd j1i" +
                    "asddddddjidsofjisodjfiwjeio[fdjweopfjkpo weo ffio kj0i wje jf jwe9j" +
                    "sdiufhuwiehfuijwehfudsjhfjsdhfjsdhjfhsdjfj;sdhjfjs  sdfhjiu sdpiuofj dsi" +
                    "asuihd ashd uiahsd hipuhw7e8 u9 0w9e ir90kwe kc w98eidfkj idsjkc 89wjeocj k8w2ej" +
                    "asdfuhwe jf9889kkkx kl2qw0l- o0v3r- ov i93i 90i9203i90ifw9e0if90i 9wi0e9id0" + "" +
                    "idsujf8jsdifoj wj9ie 9fjiw98ej j we8jf j8we98j-f w98ejf we-98j f9-8wejf jwejf89wew" +
                    "adjj j d98j92j3j980123jd 9kvck 903kfv 893j-98jd 2h3hd 78h23 7d80h23 78hd78  mc238u-" +
                    "dhh hj 0i912i90 iodskcm, im2uiecj -98u2jc-98ujewdui9j1892jsd89jjjjd jsaijd j1i" +
                    "asddddddjidsofjisodjfiwjeio[fdjweopfjkpo weo ffio kj0i wje jf jwe9j" +
                    "sdiufhuwiehfuijwehfudsjhfjsdhfjsdhjfhsdjfj;sdhjfjs  sdfhjiu sdpiuofj dsi" +
                    "asuihd ashd uiahsd hipuhw7e8 u9 0w9e ir90kwe kc w98eidfkj idsjkc 89wjeocj k8w2ej" +
                    "asdfuhwe jf9889kkkx kl2qw0l- o0v3r- ov i93i 90i9203i90ifw9e0if90i 9wi0e9id0" + "" +
                    "idsujf8jsdifoj wj9ie 9fjiw98ej j we8jf j8we98j-f w98ejf we-98j f9-8wejf jwejf89wew" +
                    "adjj j d98j92j3j980123jd 9kvck 903kfv 893j-98jd 2h3hd 78h23 7d80h23 78hd78  mc238u-" +
                    "dhh hj 0i912i90 iodskcm, im2uiecj -98u2jc-98ujewdui9j1892jsd89jjjjd jsaijd j1i" +
                    "asddddddjidsofjisodjfiwjeio[fdjweopfjkpo weo ffio kj0i wje jf jwe9j" +
                    "sdiufhuwiehfuijwehfudsjhfjsdhfjsdhjfhsdjfj;sdhjfjs  sdfhjiu sdpiuofj dsi" +
                    "asuihd ashd uiahsd hipuhw7e8 u9 0w9e ir90kwe kc w98eidfkj idsjkc 89wjeocj k8w2ej" +
                    "asdfuhwe jf9889kkkx kl2qw0l- o0v3r- ov i93i 90i9203i90ifw9e0if90i 9wi0e9id0" + "" +
                    "idsujf8jsdifoj wj9ie 9fjiw98ej j we8jf j8we98j-f w98ejf we-98j f9-8wejf jwejf89wew" +
                    "adjj j d98j92j3j980123jd 9kvck 903kfv 893j-98jd 2h3hd 78h23 7d80h23 78hd78  mc238u-" +
                    "dhh hj 0i912i90 iodskcm, im2uiecj -98u2jc-98ujewdui9j1892jsd89jjjjd jsaijd j1i"

        binding.textInputLayout.setEndIconOnClickListener {
            Toast.makeText(this, "Паззл нажат", Toast.LENGTH_LONG).show()
        }

    }


}