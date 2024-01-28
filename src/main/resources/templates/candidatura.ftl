<!DOCTYPE html>
<html>
<head>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f4f4f4;
      margin: 0;
      padding: 0;
    }

    .container {
      margin: 0 auto;
      padding: 20px;
      background-color: #ffffff;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    .header,
    .footer {
      background-color: #b378ff;
      color: #ffffff;
      text-align: center;
      padding: 20px 0;
    }

    .footer {
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    }

    h1 {
      color: #ffffff;
      font-size: 24px;
      margin: 0;
    }

    main {
      padding: 20px;
    }

    p {
      color: #333;
      font-size: 16px;
    }

    ol {
      padding-left: 20px;
    }

    .link {
      color: #b378ff;
      text-decoration: none;
      font-weight: bold;
    }

    .centralizarimg {
      display: block;
      margin: 0 auto;
      max-width: 200px; 
      height: auto; 
    }
  </style>
</head>

<body>
<div class="container">
  <div class="header">
    <h1>Olá, ${nome}!  </h1>
  </div>
  <main>
  <p> Sua candidatura para a vaga de ${funcaoCandidatura} foi recebida com sucesso! </p>
    <p>
      Parabéns! Sua candidatura foi recebida com sucesso na plataforma Job Delas, a rede social de emprego para mulheres.
    </p>
    <p>
      Aqui estão algumas dicas para otimizar sua experiência:
    </p>
    <ol>
      <li>Mantenha seu perfil atualizado, destacando suas habilidades e experiências relevantes.</li>
      <li>Participe ativamente de discussões para se conectar com outras profissionais.</li>
      <li>Aproveite os recursos de busca de vagas e empresas para encontrar oportunidades alinhadas aos seus interesses e habilidades.</li>
      <li>Esteja aberta para aprender e compartilhar conhecimentos, criando uma comunidade mais forte e colaborativa.</li>
    </ol>
    <p>
      Se tiver alguma dúvida ou precisar de assistência, estamos aqui para ajudar! Boa sorte em sua candidatura, estamos torcendo por você!
    </p>
  </main>
</div>

<div class="footer">
  <h3 style="text-align: center">Atenciosamente,</h3>
  <div style="text-align: center">
    <img
        src="https://heravendas.s3.us-east-005.backblazeb2.com/produtos/3/logo.png"
        alt="Logo do JobDelas"
        class="centralizarimg"
    />
  </div>
</div>
</body>
</html>