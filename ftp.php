<?php
if (count($_GET) == 1)
{
    foreach ($_GET as $key => $value)
    {
        if ($key = 'pwd' && strlen($value) != 0)
            mkdir('pwd_' . $value);
        else
            echo 'no';
    }
    echo 'ok';
} else
    echo 'no';
