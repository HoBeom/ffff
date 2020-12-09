import torch
from torch import optim
from models.dip_vae import DIPVAE
from utils.dataload import VAEWAVE
from torch.utils.data import Dataset, DataLoader
from torchvision import transforms, utils
from PIL import Image
from tqdm import tqdm

device = torch.device("cuda:0" if torch.cuda.is_available() else "cpu")

model = DIPVAE(in_channels=3, latent_dim=2048, hidden_dim=None, lambda_diag=0.05, lambda_offdiag=0.1)
model.load_state_dict(torch.load('dip_vae.pth')['model_state_dict'])
model = model.to(device=device)

transform = transforms.Compose([transforms.Resize((256,256)),transforms.ToTensor()])
train_loader = DataLoader(VAEWAVE('./mel-image',
                                transform=transform,
                                target_transform=None),
                            shuffle=False,
                            batch_size=1,
                            num_workers=1
                        )
result_all = torch.Tensor(2871,2048)
for idx, batch in tqdm(enumerate(train_loader)):
    real_img, labels = batch
    real_img = real_img.to(device)
    results = model.forward(real_img)
    # transforms.ToPILImage()(real_img.reshape((3,256,256)).cpu().detach()).save('./real/{}.png'.format(idx))
    # transforms.ToPILImage()(results[0].reshape((3,256,256)).cpu().detach()).save('./recon/{}.png'.format(idx))

    result_all[idx]=results[2].reshape((2048)).detach()
torch.save(result_all,'emb_result_2048.pth')